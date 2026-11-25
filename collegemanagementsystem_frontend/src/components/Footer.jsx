import { Link } from 'react-router-dom'
import { FaFacebookF, FaInstagram, FaLinkedinIn, FaYoutube } from "react-icons/fa"

function Footer(){
    return(
        <footer className="site-footer">
            <div className="footer-grid">
                <div>
                    <h4>About the college</h4>
                    <Link to="/about">About us</Link>
                    <Link to="/departments">Departments</Link>
                    <Link to="/contact">Contact</Link>
                </div>
                <div>
                    <h4>Portal</h4>
                    <Link to="/login">Student and staff login</Link>
                    <Link to="/sitemap">Sitemap</Link>
                </div>
                <div>
                    <h4>Find us</h4>
                    <p>Kumaramangalam, Tiruchengode<br />Namakkal District, Tamil Nadu</p>
                    <a href="https://mecw.org" target="_blank" rel="noopener noreferrer">College website</a>
                </div>
                <div>
                    <h4>Follow us</h4>
                    <a href="https://www.facebook.com/mahendrainstitutionsofficial" target="_blank" rel="noopener noreferrer">Facebook</a>
                    <a href="https://www.instagram.com/mahendra_institutions/?hl=en" target="_blank" rel="noopener noreferrer">Instagram</a>
                    <a href="https://www.linkedin.com/company/mahendra-educational-institutions/posts/" target="_blank" rel="noopener noreferrer">LinkedIn</a>
                    <a href="https://www.youtube.com/c/mahendrainstitutions" target="_blank" rel="noopener noreferrer">YouTube</a>
                </div>
                <div className="social">
                    <a href="https://www.facebook.com/" target="_blank" rel="noopener noreferrer" aria-label="Facebook"><FaFacebookF /></a>
                    <a href="https://www.instagram.com/" target="_blank" rel="noopener noreferrer" aria-label="Instagram"><FaInstagram /></a>
                    <a href="https://www.linkedin.com/" target="_blank" rel="noopener noreferrer" aria-label="LinkedIn"><FaLinkedinIn /></a>
                    <a href="https://www.youtube.com/" target="_blank" rel="noopener noreferrer" aria-label="YouTube"><FaYoutube /></a>
                </div>
            </div>
            <div className="footer-bottom">
                <span>© 2026 Mahendra Engineering College for Women. All rights reserved.</span>
                <span><Link to="/privacy">Privacy</Link> | <Link to="/terms">Terms of use</Link> | <Link to="/sitemap">Sitemap</Link></span>
            </div>
        </footer>
    )
}
export default Footer