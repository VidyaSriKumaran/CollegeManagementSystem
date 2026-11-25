import { useState } from "react"
import { Navigate } from "react-router-dom"
import { getRole } from "../utils/auth"

function Register(){
    const[userName,setUserName] = useState("")
    const[userPassword,setUserPassword] = useState("")
    const[message,setMessage]= useState("")
    const[userRole,setUserRole] = useState("STUDENT")
    const handleRegister = (e) => {e.preventDefault();
        setMessage("");
        fetch("http://localhost:8080/register",{method:"POST",headers:{"Content-Type":"application/json","Authorization":"Bearer "+localStorage.getItem("token")},body:JSON.stringify({userName:userName,userPassword:userPassword,userRole:userRole})})
        .then((response)=> {if(!response.ok){throw new Error("Registration Failed !!!")}return response.json()})
        .then((data)=>{
            setUserName("")
            setUserPassword("")
            setUserRole("STUDENT")
            setMessage("User Registered!!!")
            console.log(data)})
            .catch(()=>setMessage("User Registered!!!"))
    }
    const role = getRole()
    if(role !== "ADMIN"){
        return <Navigate to="/dashboard"/>
    }
    
    return(
        <div>
            <h1>Register</h1>
            <form onSubmit={handleRegister}>
            <input required placeholder='User Name'  value={userName}  onChange={(e) => setUserName(e.target.value)}/>
            <input required placeholder='User Password' type="password" value={userPassword}  onChange={(e) => setUserPassword(e.target.value)}/>
            <select value={userRole} onChange={(e) => setUserRole(e.target.value)}>
                <option value="ADMIN">Admin</option>
                <option value="FACULTY">Faculty</option>
                <option value="STUDENT">Student</option>
            </select>
            <button type="submit">Save</button>
            <p>{message}</p>
            </form>
        </div>
    )}
export default Register