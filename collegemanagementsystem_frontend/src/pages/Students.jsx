import { useEffect, useState } from "react"
import { Navigate } from "react-router-dom"
import { getRole } from "../utils/auth"

const yearOptions = [
    { value: "ALL", label: "All years" },
    { value: "1", label: "First year" },
    { value: "2", label: "Second year" },
    { value: "3", label: "Third year" },
    { value: "4", label: "Final year" }
]
const yearNames = { 1: "First year", 2: "Second year", 3: "Third year", 4: "Final year" }

function Students() {
    const[courses,setCourses] = useState([])
    const[studentId,setStudentId] = useState("")
    const[studentName,setStudentName] = useState("")
    const[studentAge,setStudentAge] = useState("")
    const[studentYear,setStudentYear] = useState("")
    const[courseId,setCourseId] = useState("")
    const[message,setMessage] = useState("")
    const[editingId,setEditingId] = useState(null)
    const[students,setStudents] = useState([])
    const[search,setSearch] = useState("")
    const[yearFilter,setYearFilter] = useState("ALL")
    const[deptFilter,setDeptFilter] = useState("ALL")
    const role = getRole()
    const canManage = role === "ADMIN" || role === "FACULTY"

    const loadStudents = () => {
        fetch("http://localhost:8080/students",{headers:{"Authorization":"Bearer "+localStorage.getItem("token")}})
        .then((response) => response.json())
        .then((data) => { if(Array.isArray(data)){ setStudents(data) } })
    }

    const loadCourses = () => {
        fetch("http://localhost:8080/courses",{headers:{"Authorization":"Bearer "+localStorage.getItem("token")}})
        .then((response) => response.json())
        .then((data) => { if(Array.isArray(data)){ setCourses(data) } })
    }

    useEffect(() => {
        if(canManage){
            loadStudents()
            loadCourses()
        }
    },[canManage])

    if(!canManage){ return <Navigate to="/dashboard" replace /> }

    const clearForm = () => {
        setStudentId("")
        setStudentName("")
        setStudentAge("")
        setStudentYear("")
        setCourseId("")
        setEditingId(null)
    }

    const handleSave = (e) => {
        e.preventDefault()
        setMessage("")
        let url = "http://localhost:8080/students"
        let method = "POST"
        if(editingId !== null){
            url = url + "/" + editingId
            method = "PUT"
        }
        fetch(url,{method:method,headers:{"Content-Type":"application/json","Authorization":"Bearer "+localStorage.getItem("token")},body:JSON.stringify({studentId:studentId,studentName:studentName,studentAge:studentAge,studentYear:studentYear === "" ? null : Number(studentYear),course:{courseId:courseId}})})
        .then((response) => {
            if(!response.ok){ throw new Error("Save failed") }
            return response.json()
        })
        .then(() => {
            loadStudents()
            clearForm()
            setMessage("Student saved")
        })
        .catch(() => setMessage("Could not save. Check that the course exists."))
    }

    const handleDelete = (id) => {
        if(!window.confirm("Delete this student?")){ return }
        fetch("http://localhost:8080/students/"+id,{method:"DELETE",headers:{"Authorization":"Bearer "+localStorage.getItem("token")}})
        .then(() => {loadStudents()})
    }

    const departments = ["ALL", ...new Set(students.map((s) => s.course?.courseName).filter(Boolean))]
    const text = search.toLowerCase()
    const visibleStudents = students.filter((student) => {
        const matchesSearch =
            (student.studentName ?? "").toLowerCase().includes(text) ||
            String(student.studentId).includes(search) ||
            (student.course?.courseName ?? "").toLowerCase().includes(text)
        const matchesYear = yearFilter === "ALL" || String(student.studentYear) === yearFilter
        const matchesDept = deptFilter === "ALL" || student.course?.courseName === deptFilter
        return matchesSearch && matchesYear && matchesDept
    })

    return (
        <div>
            <h1>Students</h1>
            <input className="search" placeholder="Search by name, id or course" value={search} onChange={(e) => setSearch(e.target.value)} />

            <div className="filter-row">
                <span className="filter-label">Department</span>
                {departments.map((d) => (
                    <button key={d} className={deptFilter === d ? "tab active" : "tab"} onClick={() => setDeptFilter(d)}>{d === "ALL" ? "All" : d}</button>
                ))}
            </div>
            <div className="filter-row">
                <span className="filter-label">Year</span>
                {yearOptions.map((y) => (
                    <button key={y.value} className={yearFilter === y.value ? "tab active" : "tab"} onClick={() => setYearFilter(y.value)}>{y.label}</button>
                ))}
            </div>

            <p>Total: {visibleStudents.length}</p>
            <table>
                <thead>
                    <tr><th>ID</th><th>Name</th><th>Age</th><th>Year</th><th>Department</th><th>Actions</th></tr>
                </thead>
                <tbody>
                    {visibleStudents.map((student) => (
                        <tr key={student.studentId}>
                            <td>{student.studentId}</td>
                            <td>{student.studentName}</td>
                            <td>{student.studentAge}</td>
                            <td>{yearNames[student.studentYear] ?? "-"}</td>
                            <td>{student.course ? student.course.courseId + " - " + student.course.courseName : "-"}</td>
                            <td>
                                <button className="small" onClick={() => {
                                    setStudentId(student.studentId)
                                    setStudentName(student.studentName ?? "")
                                    setStudentAge(student.studentAge ?? "")
                                    setStudentYear(student.studentYear ?? "")
                                    setCourseId(student.course?.courseId ?? "")
                                    setEditingId(student.studentId)
                                }}>Edit</button>
                                <button className="small danger" onClick={() => handleDelete(student.studentId)}>Delete</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>

            <form onSubmit={handleSave}>
                <h2>{editingId === null ? "Add student" : "Edit student"}</h2>
                <input required type="number" placeholder="Student Id" value={studentId} readOnly={editingId !== null} onChange={(e) => setStudentId(e.target.value)} />
                <input required placeholder="Student Name" value={studentName} onChange={(e) => setStudentName(e.target.value)} />
                <input required type="number" placeholder="Student Age" value={studentAge} onChange={(e) => setStudentAge(e.target.value)} />
                <select required value={studentYear} onChange={(e) => setStudentYear(e.target.value)}>
                    <option value="">Select year</option>
                    <option value="1">First year</option>
                    <option value="2">Second year</option>
                    <option value="3">Third year</option>
                    <option value="4">Final year</option>
                </select>
                <select required value={courseId} onChange={(e) => setCourseId(e.target.value)}>
                    <option value="">Select course</option>
                    {courses.map((c) => (
                        <option key={c.courseId} value={c.courseId}>{c.courseId} - {c.courseName}</option>
                    ))}
                </select>
                <div>
                    <button type="submit">{editingId === null ? "Add Student" : "Update Student"}</button>
                    {editingId !== null && <button type="button" className="small" onClick={clearForm}>Cancel</button>}
                </div>
                <p>{message}</p>
            </form>
        </div>
    )
}
export default Students