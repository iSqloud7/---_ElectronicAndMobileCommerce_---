import axiosInstance from "../axios/axios.js";

// axiosInstance -> "http://localhost:8080/api"
// AuthorsController -> /api/authors

const authorRepository = {
    // /api/authors/{ID} -> (GET) Get author by ID!
    findByID: async (id) => {
        return await axiosInstance.get(`/authors/${id}`);
    },

    // /api/authors/ -> (GET) Get all authors!
    findAll: async () => {
        return await axiosInstance.get("/authors/");
    },

    // api/authors/add-author -> (POST) Add a new author!
    addAuthor: async (authorData) => {
        return await axiosInstance.post("/authors/add-author", authorData);
    },

    // /api/authors/edit-author/{ID} -> (PUT) Edit an existing author!
    editAuthor: async (id, authorData) => {
        return await axiosInstance.put(`/authors/edit-author/${id}`, authorData);
    },

    // /api/authors/delete-author/{ID} -> (DELETE) Delete a author!
    deleteAuthor: async (id) => {
        return await axiosInstance.delete(`/authors/delete-author/${id}`);
    },
};

export default authorRepository;
