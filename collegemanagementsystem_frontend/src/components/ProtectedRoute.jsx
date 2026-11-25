import { getRole } from "../utils/auth";
import { Navigate } from "react-router-dom";

function ProtectedRoute({children}){
    const role = getRole()
    if(role === null){
        return <Navigate to = "/login"/>
    }
    return children
}
export default ProtectedRoute