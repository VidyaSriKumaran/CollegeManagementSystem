import { useState,useEffect } from "react";
import { Navigate } from 'react-router-dom';
import { getRole,getUserName } from "../utils/auth";

function User(){
    const[users,setUsers] = useState([])
    const loadUsers=()=>{ 
        fetch("http://localhost:8080/users",{headers:{"Authorization":"Bearer "+localStorage.getItem("token")}}).then((response) => response.json()).then((data) =>{setUsers(data)})
    }
    const handleDeleteUser = (id) => {
        fetch("http://localhost:8080/users"+"/"+id,{method:"DELETE",headers:{"Authorization":"Bearer "+localStorage.getItem("token")}}).then(() => {loadUsers()})
    }
        const role = getRole()
    useEffect(()=>
    {
        if(role === "ADMIN"){
        loadUsers()}},[role])
    if(role !== "ADMIN"){
        return <Navigate to="/dashboard"/>
    }
    const currentUser = getUserName()
    return(
        <div>
        <h1>Users</h1>
        <p>Total:{users.length}</p>
        <ul>{
            users.map((user) => (
                <li key = {user.userId}>{user.userName}-{user.userRole}
                    {user.userName !== currentUser &&(<button onClick={() => handleDeleteUser(user.userId)}>Delete</button>)}
                </li>
            ))
            }
        </ul>
        </div>
    )
}
export default User