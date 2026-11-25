import { useEffect, useState } from "react"
import { Link } from "react-router-dom"
import { getRole, getUserName } from "../utils/auth"

function Dashboard(){
    const role = getRole()
    const username = getUserName()
    const canManage = role === "ADMIN" || role === "FACULTY"
    const[counts,setCounts] = useState({students:0,courses:0,faculty:0,openComplaints:0})
    const[latest,setLatest] = useState([])

    useEffect(() => {
        const headers = {"Authorization":"Bearer "+localStorage.getItem("token")}
        const get = (path) => fetch("http://localhost:8080"+path,{headers:headers}).then((r) => r.ok ? r.json() : [])
        Promise.all([
            get("/students"),
            get("/courses"),
            get("/faculties"),
            get("/announcements"),
            canManage ? get("/complaints") : Promise.resolve([])
        ]).then(([students,courses,faculty,announcements,complaints]) => {
            setCounts({
                students:students.length,
                courses:courses.length,
                faculty:faculty.length,
                openComplaints:complaints.filter((c) => c.status === "OPEN").length
            })
            setLatest(announcements.slice(0,3))
        }).catch(() => {})
    },[canManage])

    return(
        <div>
            <h1>Welcome, {username}</h1>
            <p className="muted">You are logged in as {role}.</p>
            <div className="stat-grid">
                <Link to="/students" className="stat-card"><span className="stat-number">{counts.students}</span><span>Students</span></Link>
                <Link to="/courses" className="stat-card"><span className="stat-number">{counts.courses}</span><span>Courses</span></Link>
                <Link to="/faculty" className="stat-card"><span className="stat-number">{counts.faculty}</span><span>Faculty</span></Link>
                {canManage && (
                    <Link to="/complaints" className="stat-card"><span className="stat-number">{counts.openComplaints}</span><span>Open complaints</span></Link>
                )}
            </div>
            <h2>Latest announcements</h2>
            <ul>
                {latest.map((a) => (
                    <li key={a.announcementId}><b>{a.title}</b> - {a.category}</li>
                ))}
            </ul>
            <p><Link to="/announcements">See all announcements</Link></p>
        </div>
    )
}
export default Dashboard