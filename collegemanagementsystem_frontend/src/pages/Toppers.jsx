import { useEffect, useState } from "react"
import { getRole } from "../utils/auth"

function Toppers(){
    const[toppers,setToppers] = useState([])
    const[studentName,setStudentName] = useState("")
    const[courseName,setCourseName] = useState("")
    const[score,setScore] = useState("")
    const[academicYear,setAcademicYear] = useState("")
    const[topperRank,setTopperRank] = useState("")
    const[editingId,setEditingId] = useState(null)
    const[message,setMessage] = useState("")
    const role = getRole()
    const canManage = role === "ADMIN" || role === "FACULTY"

    const loadToppers = () => {
        fetch("http://localhost:8080/toppers",{headers:{"Authorization":"Bearer "+localStorage.getItem("token")}})
        .then((response) => response.json())
        .then((data) => {setToppers(data)})
    }
    useEffect(() => {loadToppers()},[])

    const handleSave = (e) => {
        e.preventDefault()
        setMessage("")
        let url = "http://localhost:8080/toppers"
        let method = "POST"
        if(editingId !== null){
            url = url + "/" + editingId
            method = "PUT"
        }
        fetch(url,{method:method,headers:{"Content-Type":"application/json","Authorization":"Bearer "+localStorage.getItem("token")},body:JSON.stringify({studentName:studentName,courseName:courseName,score:score,academicYear:academicYear,topperRank:topperRank})})
        .then((response) => {
            if(!response.ok){ throw new Error("Save failed") }
            return response.json()
        })
        .then(() => {
            loadToppers()
            setStudentName("")
            setCourseName("")
            setScore("")
            setAcademicYear("")
            setTopperRank("")
            setEditingId(null)
            setMessage("Topper saved")
        })
        .catch(() => setMessage("Could not save topper"))
    }

    const handleDelete = (id) => {
        if(!window.confirm("Delete this topper?")){ return }
        fetch("http://localhost:8080/toppers/"+id,{method:"DELETE",headers:{"Authorization":"Bearer "+localStorage.getItem("token")}})
        .then(() => {loadToppers()})
    }

    return(
        <div>
            <h1>Toppers</h1>
            <p>Total: {toppers.length}</p>
            <table>
                <thead>
                    <tr>
                        <th>Rank</th><th>Name</th><th>Course</th><th>Score</th><th>Year</th>
                        {canManage && <th>Actions</th>}
                    </tr>
                </thead>
                <tbody>
                    {toppers.map((topper) => (
                        <tr key={topper.topperId}>
                            <td>{topper.topperRank}</td>
                            <td>{topper.studentName}</td>
                            <td>{topper.courseName}</td>
                            <td>{topper.score}</td>
                            <td>{topper.academicYear}</td>
                            {canManage && (
                                <td>
                                    <button onClick={() => {
                                        setStudentName(topper.studentName ?? "")
                                        setCourseName(topper.courseName ?? "")
                                        setScore(topper.score ?? "")
                                        setAcademicYear(topper.academicYear ?? "")
                                        setTopperRank(topper.topperRank ?? "")
                                        setEditingId(topper.topperId)
                                    }}>Edit</button>
                                    <button onClick={() => handleDelete(topper.topperId)}>Delete</button>
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
                    <input required type="number" step="0.01" placeholder="Score" value={score} onChange={(e) => setScore(e.target.value)}/>
                    <input required placeholder="Academic Year (2025-2026)" value={academicYear} onChange={(e) => setAcademicYear(e.target.value)}/>
                    <input required type="number" placeholder="Rank" value={topperRank} onChange={(e) => setTopperRank(e.target.value)}/>
                    <button type="submit">{editingId === null ? "Add Topper" : "Update Topper"}</button>
                    <p>{message}</p>
                </form>
            )}
        </div>
    )
}
export default Toppers