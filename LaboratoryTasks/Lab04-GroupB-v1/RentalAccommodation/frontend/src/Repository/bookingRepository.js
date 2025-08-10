import axiosInstance from "../Axios/axios.js";

// Defines API operations for booking resource!
const bookingRepository = {
    // Fetch all bookings from the API!
    findAll: async () => {
        return await axiosInstance.get('/bookings');
    },
    // Fetch a specific booking by ID!
    findById: async (id) => {
        return await axiosInstance.get(`/bookings/${id}`)
    },
    // Add a new booking!
    add: async (data) => {
        return await axiosInstance.post('/bookings/addBooking', data)
    },
    // Edit an existing booking!
    edit: async (id, data) => {
        return await axiosInstance.put(`/bookings/editBooking/${id}`, data)
    },
    // Delete a booking!
    delete: async (id) => {
        return await axiosInstance.delete(`/bookings/deleteBooking/${id}`)
    },
};

export default bookingRepository;