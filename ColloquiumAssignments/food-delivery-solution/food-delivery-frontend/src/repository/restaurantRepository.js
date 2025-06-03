import axiosInstance from "../axios/axios.js";

const restaurantRepository = {
    findAll: async () => {
        return await axiosInstance.get("/restaurants");
    },
};

export default restaurantRepository;