import { useEffect, useState } from "react"
import { getRole } from "../utils/auth"

function PlacedStudents(){
    const[placements,setPlacements] = useState([])
    const[studentName,setStudentName] = useState("")
    const[courseName,setCourseName] = useState("")
    const[companyName,setCompanyName] = useState("")
    const[academicYear,setAcademicYear] = useState("")
    const[editingId,setEditingId] = useState(null)
    const[message,setMessage] = useState("")
    const role = getRole()
    const canManage = role === "ADMIN" || role === "FACULTY"

    const loadPlaced = () => {
        fetch("http://localhost:8080/placements",{headers:{"Authorization":"Bearer "+localStorage.getItem("token")}})
        .then((response) => response.json())
        .then((data) => {setPlacements(data)})
    }
    useEffect(() => {loadPlaced()},[])

    const handleSave = (e) => {
        e.preventDefault()
        setMessage("")
        let url = "http://localhost:8080/placements"
        let method = "POST"
        if(editingId !== null){
            url = url + "/" + editingId
            method = "PUT"
        }
        fetch(url,{method:method,headers:{"Content-Type":"application/json","Authorization":"Bearer "+localStorage.getItem("token")},body:JSON.stringify({studentName:studentName,courseName:courseName,companyName:companyName,academicYear:academicYear})})
        .then((response) => {
            if(!response.ok){ throw new Error("Save failed") }
            return response.json()
        })
        .then(() => {
            loadPlaced()
            setStudentName("")
            setCourseName("")
            setCompanyName("")
            setAcademicYear("")
            setEditingId(null)
            setMessage("Placement saved")
        })
        .catch(() => setMessage("Could not save placement"))
    }

    const handleDelete = (id) => {
        if(!window.confirm("Delete this placement?")){ return }
        fetch("http://localhost:8080/placements/"+id,{method:"DELETE",headers:{"Authorization":"Bearer "+localStorage.getItem("token")}})
        .then(() => {loadPlaced()})
    }

    return(
        <div>
            <h1>Placed Students</h1>
            <p>Total: {placements.length}</p>
            <table>
                <thead>
                    <tr>
                        <th>Name</th><th>Course</th><th>Company</th><th>Year</th>
                        {canManage && <th>Actions</th>}
                    </tr>
                </thead>
                <tbody>
                    {placements.map((placed) => (
                        <tr key={placed.placedId}>
                            <td>{placed.studentName}</td>
                            <td>{placed.courseName}</td>
                            <td>{placed.companyName}</td>
                            <td>{placed.academicYear}</td>
                            {canManage && (
                                <td>
                                    <button onClick={() => {
                                        setStudentName(placed.studentName ?? "")
                                        setCourseName(placed.courseName ?? "")
                                        setCompanyName(placed.companyName ?? "")
                                        setAcademicYear(placed.academicYear ?? "")
                                        setEditingId(placed.placedId)
                                    }}>Edit</button>
                                    <button onClick={() => handleDelete(placed.placedId)}>Delete</button>
                                </td>
                            )}
                        </tr>
                    ))}
                </tbody>
            </table>
            {canManage && (
                <form onSubmit={handleSave}>
                    <input required placeholder="Student Name" value={studentName} onChange={(e) => setStudentName(e.target.value)}/>
                    <input required placeholder="Course" value={courseName} onChange={(e) => setCourseName(e.target.value)}/>
                    <input required placeholder="Company" value={companyName} onChange={(e) => setCompanyName(e.target.value)}/>
                    <input required placeholder="Academic Year (2025-2026)" value={academicYear} onChange={(e) => setAcademicYear(e.target.value)}/>
                    <button type="submit">{editingId === null ? "Add Placement" : "Update Placement"}</button>
                    <p>{message}</p>
                </form>
            )}
        </div>
    )
}
export default PlacedStudents