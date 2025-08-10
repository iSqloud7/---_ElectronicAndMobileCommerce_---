import axiosInstance from "../Axios/axios.js";

// Defines API operations for host resources!
const hostRepository = {
    // Fetch all hosts from the API!
    findAll: async () => {
        return await axiosInstance.get("/hosts")
    },
    // Fetch a specific host by ID!
    findById: async (id) => {
        return await axiosInstance.get(`/hosts/${id}`)
    },
    // Add a new host!
    add: async (data) => {
        return await axiosInstance.post("/hosts/addHost", data)
    },
    // Delete a host!
    delete: async (id) => {
        return await axiosInstance.delete(`/hosts/deleteHost/${id}`)
    },
    // Edit an existing host!
    edit: async (id, data) => {
        return await axiosInstance.put(`/hosts/editHost/${id}`, data)
    },
}

export default hostRepository;