import { useEffect, useState } from "react"
import { getRole } from "../utils/auth"

function Faculties(){
    const[courses,setCourses] = useState([])
    const[facultyId,setFacultyId] = useState("")
    const[facultyName,setFacultyName] = useState("")
    const[facultyPosition,setFacultyPosition] = useState("")
    const[courseId,setCourseId] = useState("")
    const[message,setMessage] = useState("")
    const[editingId,setEditingId] = useState(null)
    const[faculties,setFaculties] = useState([])
    const role = getRole()
    const canManage = role === "ADMIN"

    const loadFaculties = () => {
        fetch("http://localhost:8080/faculties",{headers:{"Authorization":"Bearer "+localStorage.getItem("token")}})
        .then((response) => response.json())
        .then((data) => { if(Array.isArray(data)){ setFaculties(data) } })
    }
    const loadCourses = () => {
        fetch("http://localhost:8080/courses",{headers:{"Authorization":"Bearer "+localStorage.getItem("token")}})
        .then((response) => response.json())
        .then((data) => { if(Array.isArray(data)){ setCourses(data) } })
    }
    useEffect(() => {loadFaculties(); loadCourses()},[])

    const clearForm = () => {
        setFacultyId("")
        setFacultyName("")
        setFacultyPosition("")
        setCourseId("")
        setEditingId(null)
    }

    const handleSave = (e) => {
        e.preventDefault()
        setMessage("")
        let url = "http://localhost:8080/faculties"
        let method = "POST"
        if(editingId !== null){
            url = url + "/" + editingId
            method = "PUT"
        }
        fetch(url,{method:method,headers:{"Content-Type":"application/json","Authorization":"Bearer "+localStorage.getItem("token")},body:JSON.stringify({facultyId:facultyId,facultyName:facultyName,facultyPosition:facultyPosition,course:{courseId:courseId}})})
        .then((response) => {
            if(!response.ok){ throw new Error("Save failed") }
            return response.json()
        })
        .then(() => {
            loadFaculties()
            clearForm()
            setMessage("Faculty saved")
        })
        .catch(() => setMessage("Could not save. Check that the course exists."))
    }

    const handleDelete = (id) => {
        if(!window.confirm("Delete this faculty?")){ return }
        fetch("http://localhost:8080/faculties/"+id,{method:"DELETE",headers:{"Authorization":"Bearer "+localStorage.getItem("token")}})
        .then(() => {loadFaculties()})
    }

    return(
        <div>
            <h1>Faculty</h1>
            <p>Total: {faculties.length}</p>
            <table>
                <thead>
                    <tr><th>ID</th><th>Name</th><th>Position</th><th>Course</th>{canManage && <th>Actions</th>}</tr>
                </thead>
                <tbody>
                    {faculties.map((faculty) => (
                        <tr key={faculty.facultyId}>
                            <td>{faculty.facultyId}</td>
                            <td>{faculty.facultyName}</td>
                            <td>{faculty.facultyPosition}</td>
                            <td>{faculty.course ? faculty.course.courseId + " - " + faculty.course.courseName : "-"}</td>
                            {canManage && (
                                <td>
                                    <button className="small" onClick={() => {
                                        setFacultyId(faculty.facultyId)
                                        setFacultyName(faculty.facultyName ?? "")
                                        setFacultyPosition(faculty.facultyPosition ?? "")
                                        setCourseId(faculty.course?.courseId ?? "")
                                        setEditingId(faculty.facultyId)
                                    }}>Edit</button>
                                    <button className="small danger" onClick={() => handleDelete(faculty.facultyId)}>Delete</button>
                                </td>
                            )}
                        </tr>
                    ))}
                </tbody>
            </table>
            {canManage && (
                <form onSubmit={handleSave}>
                    <h2>{editingId === null ? "Add faculty" : "Edit faculty"}</h2>
                    <input required type="number" placeholder="Faculty Id" value={facultyId} readOnly={editingId !== null} onChange={(e) => setFacultyId(e.target.value)} />
                    <input required placeholder="Faculty Name" value={facultyName} onChange={(e) => setFacultyName(e.target.value)} />
                    <input required placeholder="Faculty Position" value={facultyPosition} onChange={(e) => setFacultyPosition(e.target.value)} />
                    <select required value={courseId} onChange={(e) => setCourseId(e.target.value)}>
                        <option value="">Select course</option>
                        {courses.map((c) => (
                            <option key={c.courseId} value={c.courseId}>{c.courseId} - {c.courseName}</option>
                        ))}
                    </select>
                    <div>
                        <button type="submit">{editingId === null ? "Add Faculty" : "Update Faculty"}</button>
                        {editingId !== null && <button type="button" className="small" onClick={clearForm}>Cancel</button>}
                    </div>
                    <p>{message}</p>
                </form>
            )}
        </div>
    )
}
export default Faculties