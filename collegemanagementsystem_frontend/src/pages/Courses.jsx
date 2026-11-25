import { useEffect, useState } from "react"
import { getRole } from "../utils/auth"

function Courses(){
    const[courses,setCourses] = useState([])
    const[courseId,setCourseId] = useState("")
    const[courseName,setCourseName] = useState("")
    const[editingId,setEditingId] = useState(null)
    const[message,setMessage] = useState("")
    const role = getRole()
    const canManage = role === "ADMIN"

    const loadCourses = () => {
        fetch("http://localhost:8080/courses",{headers:{"Authorization":"Bearer "+localStorage.getItem("token")}})
        .then((response) => response.json())
        .then((data) => { if(Array.isArray(data)){ setCourses(data) } })
    }
    useEffect(() => {loadCourses()},[])

    const clearForm = () => {
        setCourseId("")
        setCourseName("")
        setEditingId(null)
    }

    const handleSave = (e) => {
        e.preventDefault()
        setMessage("")
        let url = "http://localhost:8080/courses"
        let method = "POST"
        if(editingId !== null){
            url = url + "/" + editingId
            method = "PUT"
        }
        fetch(url,{method:method,headers:{"Content-Type":"application/json","Authorization":"Bearer "+localStorage.getItem("token")},body:JSON.stringify({courseId:courseId,courseName:courseName})})
        .then((response) => {
            if(!response.ok){ throw new Error("Save failed") }
            return response.json()
        })
        .then(() => {
            loadCourses()
            clearForm()
            setMessage("Course saved")
        })
        .catch(() => setMessage("Could not save the course"))
    }

    const handleDelete = (id) => {
        if(!window.confirm("Delete this course?")){ return }
        setMessage("")
        fetch("http://localhost:8080/courses/"+id,{method:"DELETE",headers:{"Authorization":"Bearer "+localStorage.getItem("token")}})
        .then((response) => {
            if(!response.ok){ throw new Error("Delete failed") }
            loadCourses()
        })
        .catch(() => setMessage("Could not delete. Students or faculty may still use this course."))
    }

    return(
        <div>
            <h1>Courses</h1>
            <p>Total: {courses.length}</p>
            {message && <p className="notice">{message}</p>}
            <table>
                <thead>
                    <tr><th>Course ID</th><th>Course Name</th>{canManage && <th>Actions</th>}</tr>
                </thead>
                <tbody>
                    {courses.map((course) => (
                        <tr key={course.courseId}>
                            <td>{course.courseId}</td>
                            <td>{course.courseName}</td>
                            {canManage && (
                                <td>
                                    <button className="small" onClick={() => {
                                        setCourseId(course.courseId)
                                        setCourseName(course.courseName ?? "")
                                        setEditingId(course.courseId)
                                    }}>Edit</button>
                                    <button className="small danger" onClick={() => handleDelete(course.courseId)}>Delete</button>
                                </td>
                            )}
                        </tr>
                    ))}
                </tbody>
            </table>
            {canManage && (
                <form onSubmit={handleSave}>
                    <h2>{editingId === null ? "Add course" : "Edit course"}</h2>
                    <input required type="number" placeholder="Course Id" value={courseId} readOnly={editingId !== null} onChange={(e) => setCourseId(e.target.value)} />
                    <input required placeholder="Course Name" value={courseName} onChange={(e) => setCourseName(e.target.value)} />
                    <div>
                        <button type="submit">{editingId === null ? "Add Course" : "Update Course"}</button>
                        {editingId !== null && <button type="button" className="small" onClick={clearForm}>Cancel</button>}
                    </div>
                </form>
            )}
        </div>
    )
}
export default Courses