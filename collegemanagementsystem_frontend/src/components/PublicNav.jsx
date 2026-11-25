import { Link, useLocation } from 'react-router-dom'
import { getRole } from '../utils/auth'

function PublicNav(){
    useLocation()
    if(getRole() !== null){ return null }
    return(
        <nav className="public-nav">
            <Link to="/">Home</Link>
            <Link to="/about">About</Link>
            <Link to="/departments">Departments</Link>
            <Link to="/contact">Contact</Link>
            <Link to="/login" className="login-link">Login</Link>
        </nav>
    )
}
export default PublicNav