import { isTokenExpired } from "./auth"

const originalFetch = window.fetch.bind(window)

window.fetch = (...args) => {
    const input = args[0]
    const url = typeof input === "string" ? input : (input.url ?? "")
    return originalFetch(...args).then((response) => {
        const token = localStorage.getItem("token")
        const isLogin = url.endsWith("/login")
        if(!isLogin && token !== null && (response.status === 401 || response.status === 403) && isTokenExpired()){
            localStorage.removeItem("token")
            sessionStorage.setItem("sessionExpired", "yes")
            window.location.href = "/login"
        }
        return response
    })
}