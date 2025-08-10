import axios from "axios";

// Create axios instance with base URL.
const axiosInstance = axios.create({
    baseURL: "http://localhost:8080/api",
    headers: {
        "Content-Type": "application/json",
    },
});

// Add an interceptor to inject the Authorization token in every request.
axiosInstance.interceptors.request.use(
    (config) => {
        // Retrieve the token from localStorage in the interceptor to ensure it is up-to-date.
        const token = localStorage.getItem("jwtToken");

        // If token exists, set it in the Authorization header.
        if (token) {
            config.headers["Authorization"] = `Bearer ${token}`;
        }

        return config;
    },

    (error) => {
        return Promise.reject(error);
    }
);

export default axiosInstance;
