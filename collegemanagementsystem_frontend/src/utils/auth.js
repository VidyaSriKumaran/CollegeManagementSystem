function readPayload(){
    const token = localStorage.getItem("token")
    if(token === null){ return null }
    try {
        const part = token.split(".")[1].replace(/-/g, "+").replace(/_/g, "/")
        return JSON.parse(atob(part))
    } catch {
        return null
    }
}

export function isTokenExpired(){
    const payload = readPayload()
    if(payload === null || !payload.exp){ return true }
    return payload.exp * 1000 < Date.now()
}

export function getRole(){
    const payload = readPayload()
    if(payload === null || isTokenExpired()){ return null }
    return payload.role
}

export function getUserName(){
    const payload = readPayload()
    if(payload === null || isTokenExpired()){ return null }
    return payload.sub
}