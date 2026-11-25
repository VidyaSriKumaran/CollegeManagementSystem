import { useState } from 'react'
import { useNavigate,Navigate } from 'react-router-dom'
import { getRole } from '../utils/auth'

function Login() {
  const[userName,setUserName] = useState("")
  const[userPassword,setUserPassword] = useState("")
  const[errorMessage,setErrorMessage] = useState("")
  const navigate = useNavigate()
  const handleLogin = (e) => {e.preventDefault();
    setErrorMessage("");
    fetch("http://localhost:8080/login", { method: "POST",headers:{"Content-Type":"application/json"}, body: JSON.stringify({userName:userName,userPassword:userPassword})}).then((response) => {if(!response.ok){throw new Error("Login Failed☹️!!!")}return response.text()}).then((data) => {localStorage.setItem("token",data); navigate("/dashboard")}).catch(() => setErrorMessage("Login Failed☹️!!!"));
  } 
  const[expired] = useState(() => sessionStorage.getItem("sessionExpired") === "yes")
  const role = getRole()
  if(role !== null){
    return <Navigate to = "/dashboard"/>
  }
  return(
    <div className="login-page">
        <div className="login-art">
            <img src="/images/login.jpg" alt="Campus" onError={(e) => { e.currentTarget.style.display = "none" }} />
        </div>
        <div className="login-card">
            <h1>Student and staff login</h1>
            {expired && <p className="notice">Session expired. Please log in again.</p>}
            <form onSubmit={handleLogin}>    
      <input required placeholder='User Name' value={userName} onChange={(e) => setUserName(e.target.value)}/>
      <br />
      <input required placeholder='Password' type='password' value={userPassword} onChange={(e) => setUserPassword(e.target.value)}/>
      <br />
      <button>Login</button>
      <p>{errorMessage}</p>
    </form>
        </div>
    </div>
)
}
export default Login
