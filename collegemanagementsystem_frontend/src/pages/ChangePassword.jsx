import { useState } from "react"

function ChangePassword(){
    const[currentPassword,setCurrentPassword] = useState("")
    const[newPassword,setNewPassword] = useState("")
    const[confirmPassword,setConfirmPassword] = useState("")
    const[message,setMessage] = useState("")

    const handleSubmit = (e) => {
        e.preventDefault()
        setMessage("")
        if(newPassword.length < 6){ setMessage("The new password must have at least 6 characters"); return }
        if(newPassword !== confirmPassword){ setMessage("The two new passwords are not the same"); return }
        fetch("http://localhost:8080/users/password",{method:"PUT",headers:{"Content-Type":"application/json","Authorization":"Bearer "+localStorage.getItem("token")},body:JSON.stringify({currentPassword:currentPassword,newPassword:newPassword})})
        .then((response) => response.text().then((text) => ({ ok: response.ok, text: text })))
        .then((result) => {
            if(result.ok){
                setCurrentPassword("")
                setNewPassword("")
                setConfirmPassword("")
                setMessage("Password changed")
            } else {
                setMessage(result.text || "Could not change the password")
            }
        })
        .catch(() => setMessage("Could not change the password"))
    }

    return(
        <div className="page">
            <h1>Change Password</h1>
            <form onSubmit={handleSubmit}>
                <input required type="password" placeholder="Current password" value={currentPassword} onChange={(e) => setCurrentPassword(e.target.value)} />
                <input required type="password" placeholder="New password (at least 6 characters)" value={newPassword} onChange={(e) => setNewPassword(e.target.value)} />
                <input required type="password" placeholder="Confirm new password" value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)} />
                <button type="submit">Change Password</button>
                <p>{message}</p>
            </form>
        </div>
    )
}
export default ChangePassword