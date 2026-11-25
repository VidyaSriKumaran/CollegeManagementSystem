import { useEffect, useState } from "react"
import { Link, Navigate } from "react-router-dom"
import { getRole } from "../utils/auth"

const slides = ["/images/campus1.jpg", "/images/campus2.jpg"]
const facts = [
    { big: "2008", small: "Year the college started" },
    { big: "AICTE", small: "Approved institution" },
    { big: "Anna Univ.", small: "Affiliated, Chennai" },
    { big: "4", small: "Undergraduate programmes" }
]

function Welcome(){
    const[index,setIndex] = useState(0)
    useEffect(() => {
        const timer = setInterval(() => {
            setIndex((current) => (current + 1) % slides.length)
        }, 4000)
        return () => clearInterval(timer)
    },[])
    const role = getRole()
    if(role !== null){ return <Navigate to="/dashboard" /> }
    return(
        <div>
            <section className="hero">
                <img src={slides[index]} alt="Campus" className="hero-img" onError={(e) => { e.currentTarget.style.display = "none" }} />
                <div className="hero-text">
                    <h1>Empowering women through engineering education</h1>
                    <p>A supportive place to learn practical skills and build a career.</p>
                    <Link to="/departments" className="btn">Explore departments</Link>
                </div>
            </section>
            <div className="facts">
                {facts.map((f) => (
                    <div className="fact" key={f.small}><b>{f.big}</b>{f.small}</div>
                ))}
            </div>
            <h2>Our departments</h2>
            <div className="feature-grid">
                <div className="feature-card"><h3>CSE</h3><p>Computer Science and Engineering</p></div>
                <div className="feature-card"><h3>ECE</h3><p>Electronics and Communication Engineering</p></div>
                <div className="feature-card"><h3>IT</h3><p>Information Technology</p></div>
                <div className="feature-card"><h3>Science</h3><p>Science and Humanities</p></div>
            </div>
            <h2>Students and staff</h2>
            <p>Use your college account to open the portal.</p>
            <Link to="/login" className="btn">Student and staff login</Link>
        </div>
    )
}
export default Welcome