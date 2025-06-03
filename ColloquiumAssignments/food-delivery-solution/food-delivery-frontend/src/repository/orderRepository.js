import axiosInstance from "../axios/axios.js";

const orderRepository = {
    findPending: async () => {
        return await axiosInstance.get("/orders/pending");
    },
    confirmPendingOrder: async () => {
        return await axiosInstance.put("/orders/pending/confirm");
    },
    cancelPendingOrder: async () => {
        return await axiosInstance.put("/orders/pending/cancel");
    },
};

export default orderRepository;