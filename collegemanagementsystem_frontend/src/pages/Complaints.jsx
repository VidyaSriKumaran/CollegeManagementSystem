import { useEffect, useState } from "react"
import { getRole } from "../utils/auth"

function Complaints(){
    const[mine,setMine] = useState([])
    const[all,setAll] = useState([])
    const[subject,setSubject] = useState("")
    const[description,setDescription] = useState("")
    const[message,setMessage] = useState("")
    const[editingId,setEditingId] = useState(null)
    const[status,setStatus] = useState("OPEN")
    const[reply,setReply] = useState("")
    const role = getRole()
    const canManage = role === "ADMIN" || role === "FACULTY"

    const loadMine = () => {
        fetch("http://localhost:8080/complaints/mine",{headers:{"Authorization":"Bearer "+localStorage.getItem("token")}})
        .then((response) => response.json())
        .then((data) => {setMine(data)})
    }
    const loadAll = () => {
        fetch("http://localhost:8080/complaints",{headers:{"Authorization":"Bearer "+localStorage.getItem("token")}})
        .then((response) => response.json())
        .then((data) => {setAll(data)})
    }
    useEffect(() => {
        loadMine()
        if(canManage){ loadAll() }
    },[canManage])

    const reloadLists = () => {
        loadMine()
        if(canManage){ loadAll() }
    }

    const handleSubmit = (e) => {
        e.preventDefault()
        setMessage("")
        fetch("http://localhost:8080/complaints",{method:"POST",headers:{"Content-Type":"application/json","Authorization":"Bearer "+localStorage.getItem("token")},body:JSON.stringify({subject:subject,description:description})})
        .then((response) => {
            if(!response.ok){ throw new Error("Failed") }
            return response.json()
        })
        .then(() => {
            reloadLists()
            setSubject("")
            setDescription("")
            setMessage("Complaint submitted")
        })
        .catch(() => setMessage("Could not submit complaint"))
    }

    const handleUpdate = (e) => {
        e.preventDefault()
        fetch("http://localhost:8080/complaints/"+editingId,{method:"PUT",headers:{"Content-Type":"application/json","Authorization":"Bearer "+localStorage.getItem("token")},body:JSON.stringify({status:status,reply:reply})})
        .then(() => {
            reloadLists()
            setEditingId(null)
            setStatus("OPEN")
            setReply("")
        })
    }

    const handleDelete = (id) => {
        if(!window.confirm("Delete this complaint?")){ return }
        fetch("http://localhost:8080/complaints/"+id,{method:"DELETE",headers:{"Authorization":"Bearer "+localStorage.getItem("token")}})
        .then(() => {reloadLists()})
    }

    const showDate = (value) => new Date(value).toLocaleDateString("en-IN",{day:"numeric",month:"short",year:"numeric"})

    return(
        <div>
            <h1>Complaint Box</h1>
            <h2>Write a complaint</h2>
            <form onSubmit={handleSubmit}>
                <input required placeholder="Subject" value={subject} onChange={(e) => setSubject(e.target.value)}/>
                <textarea required placeholder="Describe your complaint" value={description} onChange={(e) => setDescription(e.target.value)}/>
                <button type="submit">Submit Complaint</button>
                <p>{message}</p>
            </form>

            <h2>My complaints</h2>
            <ul>
                {mine.map((c) => (
                    <li key={c.complaintId}>
                        <b>{c.subject}</b> - {c.status} - {showDate(c.createdAt)}
                        <p>{c.description}</p>
                        {c.reply && <p>Reply: {c.reply}</p>}
                    </li>
                ))}
            </ul>

            {canManage && (
                <div>
                    <h2>All complaints</h2>
                    <ul>
                        {all.map((c) => (
                            <li key={c.complaintId}>
                                <b>{c.subject}</b> - {c.status} - {showDate(c.createdAt)} - From: {c.submittedBy}
                                <p>{c.description}</p>
                                {c.reply && <p>Reply: {c.reply}</p>}
                                <button onClick={() => {
                                    setEditingId(c.complaintId)
                                    setStatus(c.status ?? "OPEN")
                                    setReply(c.reply ?? "")
                                }}>Update</button>
                                {role === "ADMIN" && <button onClick={() => handleDelete(c.complaintId)}>Delete</button>}
                            </li>
                        ))}
                    </ul>
                    {editingId !== null && (
                        <form onSubmit={handleUpdate}>
                            <select value={status} onChange={(e) => setStatus(e.target.value)}>
                                <option value="OPEN">Open</option>
                                <option value="IN_PROGRESS">In progress</option>
                                <option value="RESOLVED">Resolved</option>
                            </select>
                            <textarea placeholder="Reply" value={reply} onChange={(e) => setReply(e.target.value)}/>
                            <button type="submit">Save Update</button>
                            <button type="button" onClick={() => setEditingId(null)}>Cancel</button>
                        </form>
                    )}
                </div>
            )}
        </div>
    )
}
export default Complaints