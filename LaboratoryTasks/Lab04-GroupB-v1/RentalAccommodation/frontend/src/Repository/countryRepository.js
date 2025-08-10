import axiosInstance from "../Axios/axios.js";

// Defines API operations for country resources!
const countryRepository = {
    // Fetch all countries from the API!
    findAll: async () => {
        return await axiosInstance.get("/countries")
    },
    // Fetch a specific country by ID!
    findById: async (id) => {
        return await axiosInstance.get(`/countries/${id}`)
    },
    // Add a new country!
    add: async (data) => {
        return await axiosInstance.post("/countries/addCountry", data)
    },
    // Edit an existing country!
    edit: async (id, data) => {
        return await axiosInstance.put(`/countries/editCountry/${id}`, data)
    },
    // Delete a country!
    delete:
        async (id) => {
            return await axiosInstance.delete(`/countries/deleteCountry/${id}`)
        },
};

export default countryRepository;