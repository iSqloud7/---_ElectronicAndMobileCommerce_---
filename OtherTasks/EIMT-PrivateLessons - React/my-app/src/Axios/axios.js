import axios from "axios"

const axiosInstance = axios.create({
    // baseUrl: "http://localhost:8080/api",
    baseURL: "https://jsonplaceholder.typicode.com",
    headers: {
        "Content-Type": "applicaton/json",
    },
})

export default axiosInstance