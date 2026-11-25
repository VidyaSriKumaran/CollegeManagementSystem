const departments = [
    { code: "CSE", name: "Computer Science and Engineering", programmes: "B.E. Computer Science and Engineering, M.E. Computer Science and Engineering", areas: ["Programming", "Data structures", "Databases", "Software engineering"], careers: "Software developer, data analyst, systems engineer" },
    { code: "ECE", name: "Electronics and Communication Engineering", programmes: "B.E. Electronics and Communication Engineering", areas: ["Electronic circuits", "Communication systems", "Embedded systems", "Signal processing"], careers: "Electronics engineer, network engineer, embedded developer" },
    { code: "IT", name: "Information Technology", programmes: "Undergraduate programme in Information Technology", areas: ["Web technologies", "Networks", "Cloud computing", "Information systems"], careers: "Web developer, network administrator, cloud engineer" },
    { code: "S and H", name: "Science and Humanities", programmes: "Foundation subjects for all engineering programmes", areas: ["Mathematics", "Physics", "Chemistry", "English"], careers: "Builds the base for every engineering branch" }
]

function Departments(){
    return(
        <div className="page">
            <h1>Departments</h1>
            <p className="muted">Started in 2008. Check the programme details against the college website.</p>
            <div className="feature-grid">
                {departments.map((d) => (
                    <div className="feature-card" key={d.code}>
                        <h3>{d.code}</h3>
                        <p><b>{d.name}</b></p>
                        <p>{d.programmes}</p>
                        <div className="chips">{d.areas.map((a) => (<em key={a}>{a}</em>))}</div>
                        <p><b>Careers:</b> {d.careers}</p>
                    </div>
                ))}
            </div>
        </div>
    )
}
export default Departments