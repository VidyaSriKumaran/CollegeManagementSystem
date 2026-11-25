import { useEffect, useState } from "react"
import { getRole } from "../utils/auth"
import { useSearchParams } from "react-router-dom"

const categories = [
    { value: "ALL", label: "All" },
    { value: "GENERAL", label: "General" },
    { value: "EXAM", label: "Exam" },
    { value: "PLACEMENT", label: "Placement" },
    { value: "EVENT", label: "Event" },
    { value: "RESULT", label: "Result" }
]

function Announcements(){
    const[openId,setOpenId] = useState(null)
    const[searchParams,setSearchParams] = useSearchParams()
const selectedCategory = searchParams.get("category") ?? "ALL"
const setSelectedCategory = (value) => setSearchParams(value === "ALL" ? {} : { category: value })
    const[now] = useState(() => Date.now())
    const[category,setCategory] = useState("GENERAL")
    const[title,setTitle] = useState("")
    const[description,setDescription] = useState("")
    const[link,setLink] = useState("")
    const[editingId,setEditingId] = useState(null)
    const[showForm,setShowForm] = useState(false)
    const[message,setMessage] = useState("")
    const[announcements,setAnnouncements] = useState([])
    const role = getRole()
    const canManage = role === "ADMIN" || role === "FACULTY"

    const loadAnnouncements = () => {
        fetch("http://localhost:8080/announcements",{headers:{"Authorization":"Bearer "+localStorage.getItem("token")}})
        .then((response) => response.json())
        .then((data) => { if(Array.isArray(data)){ setAnnouncements(data) } })
    }
    useEffect(() => {loadAnnouncements()},[])

    const clearForm = () => {
        setTitle("")
        setDescription("")
        setLink("")
        setCategory("GENERAL")
        setEditingId(null)
        setShowForm(false)
    }

    const handleSave = (e) => {
        e.preventDefault()
        setMessage("")
        let url = "http://localhost:8080/announcements"
        let method = "POST"
        if(editingId !== null){
            url = url + "/" + editingId
            method = "PUT"
        }
        fetch(url,{method:method,headers:{"Content-Type":"application/json","Authorization":"Bearer "+localStorage.getItem("token")},body:JSON.stringify({title:title,category:category,description:description,link:link})})
        .then((response) => {
            if(!response.ok){ throw new Error("Save failed") }
            return response.json()
        })
        .then(() => {
            loadAnnouncements()
            clearForm()
            setMessage("Announcement saved")
        })
        .catch(() => setMessage("Could not save the announcement"))
    }

    const handleDelete = (id) => {
        if(!window.confirm("Delete this announcement?")){ return }
        fetch("http://localhost:8080/announcements/"+id,{method:"DELETE",headers:{"Authorization":"Bearer "+localStorage.getItem("token")}})
        .then(() => {loadAnnouncements()})
    }

    const startEdit = (a) => {
        setTitle(a.title ?? "")
        setDescription(a.description ?? "")
        setLink(a.link ?? "")
        setCategory(a.category ?? "GENERAL")
        setEditingId(a.announcementId)
        setShowForm(true)
    }

    const showDate = (value) => new Date(value).toLocaleDateString("en-IN",{day:"numeric",month:"short",year:"numeric"})
    const isNew = (a) => (now - new Date(a.createdAt)) < 3 * 24 * 60 * 60 * 1000
    const visible = announcements.filter((a) => selectedCategory === "ALL" || a.category === selectedCategory)

    return(
        <div>
            <div className="page-head">
                <h1>Announcements</h1>
                {canManage && (
                    <button className="compose" onClick={() => { if(showForm){ clearForm() } else { setShowForm(true) } }}>
                        {showForm ? "Close" : "+ New announcement"}
                    </button>
                )}
            </div>

            <div className="tabs">
                {categories.map((c) => (
                    <button key={c.value} className={selectedCategory === c.value ? "tab active" : "tab"} onClick={() => setSelectedCategory(c.value)}>{c.label}</button>
                ))}
            </div>

            {message && <p className="notice">{message}</p>}

            {canManage && showForm && (
                <form onSubmit={handleSave}>
                    <h2>{editingId === null ? "New announcement" : "Edit announcement"}</h2>
                    <input required placeholder="Title" value={title} onChange={(e) => setTitle(e.target.value)} />
                    <select value={category} onChange={(e) => setCategory(e.target.value)}>
                        <option value="RESULT">Result</option>
                        <option value="GENERAL">General</option>
                        <option value="EXAM">Exam</option>
                        <option value="PLACEMENT">Placement</option>
                        <option value="EVENT">Event</option>
                    </select>
                    <textarea required placeholder="Description" value={description} onChange={(e) => setDescription(e.target.value)} />
                    <input placeholder="Link (optional, starts with https://)" value={link} onChange={(e) => setLink(e.target.value)} />
                    <div>
                        <button type="submit">{editingId === null ? "Publish" : "Update"}</button>
                        <button type="button" className="small" onClick={clearForm}>Cancel</button>
                    </div>
                </form>
            )}

            <div className="inbox">
                {visible.length === 0 && <p className="muted" style={{padding:"16px"}}>No announcements here.</p>}
                {visible.map((a) => (
                    <div className={openId === a.announcementId ? "mail open" : "mail"} key={a.announcementId}>
                        <div className="mail-row" onClick={() => setOpenId(openId === a.announcementId ? null : a.announcementId)}>
                            <span className="mail-from">{a.postedBy}</span>
                            <span className={"tag tag-" + String(a.category).toLowerCase()}>{a.category}</span>
                            <span className="mail-subject"><b>{a.title}</b><span className="mail-snippet"> - {(a.description ?? "").slice(0, 80)}</span></span>
                            {isNew(a) && <span className="new-badge">New</span>}
                            <span className="mail-date">{showDate(a.createdAt)}</span>
                        </div>
                        {openId === a.announcementId && (
                            <div className="mail-body">
                                <h3>{a.title}</h3>
                                <p className="mail-meta">Posted by {a.postedBy} on {showDate(a.createdAt)}</p>
                                <p>{a.description}</p>
                                {a.link && a.link.startsWith("https://") && (
                                    <a className="link-btn" href={a.link} target="_blank" rel="noopener noreferrer">Open Link</a>
                                )}
                                {canManage && (
                                    <div className="mail-actions">
                                        <button className="small" onClick={() => startEdit(a)}>Edit</button>
                                        <button className="small danger" onClick={() => handleDelete(a.announcementId)}>Delete</button>
                                    </div>
                                )}
                            </div>
                        )}
                    </div>
                ))}
            </div>
        </div>
    )
}
export default Announcements