import { Link } from 'react-router-dom'

function Header(){
    return(
        <header className="site-header">
            <Link to="/">
                <img src="/images/logo.png" alt="Mahendra Engineering College for Women (Autonomous)" className="site-logo" />
            </Link>
            <span className="portal-pill">College Management Portal</span>
        </header>
    )
}
export default Header