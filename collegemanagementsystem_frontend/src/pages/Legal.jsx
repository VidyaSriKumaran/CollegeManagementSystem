import { Link } from 'react-router-dom'

export function Privacy(){
    return(
        <div className="page">
            <h1>Privacy Policy</h1>
            <p>This portal keeps the account details, academic records and complaints that the college services need. Passwords are stored as hashes and are never shown.</p>
            <p>Complaints are private. Faculty cannot see the name of the student who sent a complaint.</p>
            <p className="muted">This is a sample policy written for a student project.</p>
        </div>
    )
}

export function Terms(){
    return(
        <div className="page">
            <h1>Terms of Use</h1>
            <p>Use the portal only with your own account. Do not share your password. Accounts that misuse the portal can be removed by the administrator.</p>
            <p className="muted">This is a sample text written for a student project.</p>
        </div>
    )
}

export function Sitemap(){
    return(
        <div className="page">
            <h1>Sitemap</h1>
            <ul>
                <li><Link to="/">Home</Link></li>
                <li><Link to="/about">About</Link></li>
                <li><Link to="/departments">Departments</Link></li>
                <li><Link to="/contact">Contact</Link></li>
                <li><Link to="/login">Login</Link></li>
            </ul>
        </div>
    )
}