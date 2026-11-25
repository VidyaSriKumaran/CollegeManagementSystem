import { Link, useLocation, useNavigate } from "react-router-dom"
import { getRole } from "../utils/auth"

function Navbar(){
    useLocation()
    const navigate = useNavigate()
    const role = getRole()
    if(role === null){ return null }
    const canSeeStudents = role === "ADMIN" || role === "FACULTY"

    const handleLogout = () => {
        localStorage.removeItem("token")
        navigate("/login")
    }

    return(
        <nav>
            <Link to="/dashboard">Dashboard</Link>
            {canSeeStudents && <Link to="/students">Students</Link>}
            <Link to="/courses">Courses</Link>
            <Link to="/faculty">Faculty</Link>
            <Link to="/announcements">Announcements</Link>
            <Link to="/announcements?category=RESULT">Results</Link>
            <Link to="/materials">Study Materials</Link>
            <Link to="/toppers">Toppers</Link>
            <Link to="/placements">Placed Students</Link>
            <Link to="/complaints">Complaints</Link>
            {role === "ADMIN" && <Link to="/users">Users</Link>}
            {role === "ADMIN" && <Link to="/register">Register</Link>}
            <Link to="/change-password">Change Password</Link>
            <button onClick={handleLogout}>Logout</button>
        </nav>
    )
}
export default Navbar