import axiosInstance from "../axios/axios.js";

// axiosInstance -> "http://localhost:8080/api"
// CountriesController -> /api/countries

const countryRepository = {
    // /api/countries/{ID} -> (GET) Get country by ID!
    findByID: async (id) => {
        return await axiosInstance.get(`/countries/${id}`);
    },

    // /api/countries/ -> (GET) Get all countries!
    findAll: async () => {
        return await axiosInstance.get("/countries/");
    },

    // api/countries/add-country -> (POST) Add a new country!
    addCountry: async (countryData) => {
        return await axiosInstance.post("/countries/add-country", countryData);
    },

    // /api/countries/edit-country/{ID} -> (PUT) Edit an existing country!
    editCountry: async (id, countryData) => {
        return await axiosInstance.put(`/countries/edit-country/${id}`, countryData);
    },

    // /api/countries/delete-country/{ID} -> (DELETE) Delete a country!
    deleteCountry: async (id) => {
        return await axiosInstance.delete(`/countries/delete-country/${id}`);
    },
};

export default countryRepository;
