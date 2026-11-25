import { useEffect, useState } from "react"
import { getRole } from "../utils/auth"

function StudyMaterials(){
    const[search,setSearch] = useState("")
    const[materials,setMaterials] = useState([])
    const[openSubject,setOpenSubject] = useState(null)
    const[subject,setSubject] = useState("")
    const[title,setTitle] = useState("")
    const[file,setFile] = useState(null)
    const[fileKey,setFileKey] = useState(0)
    const[message,setMessage] = useState("")
    const role = getRole()
    const canManage = role === "ADMIN" || role === "FACULTY"

    const loadMaterials = () => {
        fetch("http://localhost:8080/materials",{headers:{"Authorization":"Bearer "+localStorage.getItem("token")}})
        .then((response) => response.json())
        .then((data) => { if(Array.isArray(data)){ setMaterials(data) } })
    }
    useEffect(() => {loadMaterials()},[])

    const handleUpload = (e) => {
        e.preventDefault()
        setMessage("")
        if(!file){ setMessage("Please choose a file"); return }
        const name = file.name.toLowerCase()
        const allowed = [".pdf",".doc",".docx",".ppt",".pptx"]
        if(!allowed.some((ext) => name.endsWith(ext))){ setMessage("Only PDF, Word or PowerPoint files are allowed"); return }
        if(file.size > 5 * 1024 * 1024){ setMessage("The file must be 5 MB or smaller"); return }
        const formData = new FormData()
        formData.append("subject", subject)
        formData.append("title", title)
        formData.append("file", file)
        fetch("http://localhost:8080/materials",{method:"POST",headers:{"Authorization":"Bearer "+localStorage.getItem("token")},body:formData})
        .then((response) => {
            if(!response.ok){
                return response.text().then((text) => { throw new Error(text) })
            }
        })
        .then(() => {
            loadMaterials()
            setOpenSubject(subject.trim())
            setSubject("")
            setTitle("")
            setFile(null)
            setFileKey(fileKey + 1)
            setMessage("File uploaded")
        })
        .catch((error) => setMessage(error.message || "Could not upload the file"))
    }

    const handleDownload = (material) => {
        fetch("http://localhost:8080/materials/"+material.materialId+"/download",{headers:{"Authorization":"Bearer "+localStorage.getItem("token")}})
        .then((response) => {
            if(!response.ok){ throw new Error("Download failed") }
            return response.blob()
        })
        .then((blob) => {
            const url = window.URL.createObjectURL(blob)
            const link = document.createElement("a")
            link.href = url
            link.download = material.fileName
            document.body.appendChild(link)
            link.click()
            link.remove()
            window.URL.revokeObjectURL(url)
        })
        .catch(() => setMessage("Could not download the file"))
    }

    const handleDelete = (id) => {
        if(!window.confirm("Delete this file?")){ return }
        fetch("http://localhost:8080/materials/"+id,{method:"DELETE",headers:{"Authorization":"Bearer "+localStorage.getItem("token")}})
        .then(() => {loadMaterials()})
    }

    const sizeText = (bytes) => bytes > 1024 * 1024 ? (bytes / 1048576).toFixed(1) + " MB" : Math.max(1, Math.round(bytes / 1024)) + " KB"
    const showDate = (value) => new Date(value).toLocaleDateString("en-IN",{day:"numeric",month:"short",year:"numeric"})
    const text = search.trim().toLowerCase()
    const filtered = materials.filter((m) =>
    (m.subject ?? "").toLowerCase().includes(text) ||
    (m.title ?? "").toLowerCase().includes(text) ||
    (m.fileName ?? "").toLowerCase().includes(text)
    )
    const allSubjects = [...new Set(materials.map((m) => m.subject))].sort()
    const subjects = [...new Set(filtered.map((m) => m.subject))].sort()
    return(
        <div>
            <h1>Study Materials</h1>
            <p className="muted">Click a subject to see its materials.</p>
            {message && <p className="notice">{message}</p>}
            <input className="search" placeholder="Search by subject, title or file name" value={search} onChange={(e) => setSearch(e.target.value)} />
            

            <div className="inbox">
                {subjects.length === 0 && <p className="muted" style={{padding:"16px"}}>{text !== "" ? "No materials match your search." : "No study materials yet."}</p>}
                {subjects.map((s) => {
                    const items = filtered.filter((m) => m.subject === s)
                    const isOpen = text !== "" || openSubject === s                    
                    return(
                        <div className={isOpen ? "mail open" : "mail"} key={s}>
                            <div className="mail-row" onClick={() => setOpenSubject(openSubject === s ? null : s)}>
                                <span className="mail-subject"><b>{s}</b></span>
                                <span className="mail-date">{items.length} file{items.length === 1 ? "" : "s"}</span>
                            </div>
                            {isOpen && (
                                <div className="mail-body">
                                    <table>
                                        <thead>
                                            <tr><th>Title</th><th>File</th><th>Size</th><th>Uploaded by</th><th>Date</th><th>Actions</th></tr>
                                        </thead>
                                        <tbody>
                                            {items.map((m) => (
                                                <tr key={m.materialId}>
                                                    <td>{m.title}</td>
                                                    <td>{m.fileName}</td>
                                                    <td>{sizeText(m.fileSize)}</td>
                                                    <td>{m.uploadedBy}</td>
                                                    <td>{showDate(m.createdAt)}</td>
                                                    <td>
                                                        <button className="small" onClick={() => handleDownload(m)}>Download</button>
                                                        {canManage && <button className="small danger" onClick={() => handleDelete(m.materialId)}>Delete</button>}
                                                    </td>
                                                </tr>
                                            ))}
                                        </tbody>
                                    </table>
                                </div>
                            )}
                        </div>
                    )
                })}
            </div>

            {canManage && (
                <form onSubmit={handleUpload}>
                    <h2>Upload study material</h2>
                    <input required list="subject-list" placeholder="Subject (choose or type a new one)" value={subject} onChange={(e) => setSubject(e.target.value)} />
                    <datalist id="subject-list">
                        {allSubjects.map((s) => (<option key={s} value={s} />))}
                    </datalist>
                    <input required placeholder="Title" value={title} onChange={(e) => setTitle(e.target.value)} />
                    <input key={fileKey} required type="file" accept=".pdf,.doc,.docx,.ppt,.pptx" onChange={(e) => setFile(e.target.files[0] ?? null)} />
                    <p className="muted">PDF, Word or PowerPoint, up to 5 MB.</p>
                    <button type="submit">Upload</button>
                </form>
            )}
        </div>
    )
}
export default StudyMaterials