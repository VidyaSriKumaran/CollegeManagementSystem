import { useState } from "react"
import { FaBullhorn, FaUniversity, FaWhatsapp, FaPhoneAlt, FaEnvelope, FaGraduationCap, FaAngleDoubleRight, FaAngleDoubleLeft } from "react-icons/fa"

function FloatingPanel(){
    const[open,setOpen] = useState(true)
    return(
        <aside className={open ? "float-panel" : "float-panel closed"}>
            <button className="fp-toggle" onClick={() => setOpen(!open)} aria-label={open ? "Fold the panel" : "Open the panel"}>
                {open ? <FaAngleDoubleRight /> : <FaAngleDoubleLeft />}
            </button>
            <div className="fp-items">
                <a href="https://mecw.org" target="_blank" rel="noopener noreferrer" className="fp-item fp-admission"><FaBullhorn /> Admission 2026 - 2027</a>
                <div className="fp-item fp-code"><FaUniversity /> TNEA Code 2638</div>
                <a href="https://wa.me/918985712345" target="_blank" rel="noopener noreferrer" className="fp-item fp-whatsapp"><FaWhatsapp /> +91 89857 12345</a>
                <a href="https://wa.me/917382812345" target="_blank" rel="noopener noreferrer" className="fp-item fp-whatsapp"><FaWhatsapp /> +91 73828 12345</a>
                <a href="tel:+914288247007" className="fp-item"><FaPhoneAlt /> +91 4288 247007</a>
                <a href="mailto:info@mecw.org" className="fp-item"><FaEnvelope /> info@mecw.org</a>
                <a href="https://mecw.org" target="_blank" rel="noopener noreferrer" className="fp-item fp-apply"><FaGraduationCap /> Apply Online</a>
            </div>
        </aside>
    )
}
export default FloatingPanel