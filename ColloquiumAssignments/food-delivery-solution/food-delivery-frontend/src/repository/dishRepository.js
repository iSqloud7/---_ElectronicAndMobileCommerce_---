import axiosInstance from "../axios/axios.js";

const dishRepository = {
    findAll: async () => {
        return await axiosInstance.get("/dishes");
    },
    findByIdWithDetails: async (id) => {
        return await axiosInstance.get(`/dishes/${id}/details`);
    },
    add: async (data) => {
        return await axiosInstance.post("/dishes/add", data);
    },
    edit: async (id, data) => {
        return await axiosInstance.put(`/dishes/${id}/edit`, data);
    },
    delete: async (id) => {
        return await axiosInstance.delete(`/dishes/${id}/delete`);
    },
    addToOrder: async (id) => {
        return await axiosInstance.post(`/dishes/${id}/add-to-order`);
    },
    removeFromOrder: async (id) => {
        return await axiosInstance.post(`/dishes/${id}/remove-from-order`);
    },
};

export default dishRepository;