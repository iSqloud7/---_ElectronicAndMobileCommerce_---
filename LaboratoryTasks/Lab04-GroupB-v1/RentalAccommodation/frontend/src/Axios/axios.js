import axios from "axios";

// Sets up the axios instance with base URL and default headers!
const axiosInstance = axios.create({
    baseURL: "http://localhost:8080/api",
    headers: {
        "Content-Type": "application/json",
    }
})

export default axiosInstance;