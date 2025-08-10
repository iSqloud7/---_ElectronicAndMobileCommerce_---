import axiosInstance from "../axios/axios.js";

// axiosInstance -> "http://localhost:8080/api"
// BooksController -> /api/books

// OBJECT
const bookRepository = {
    // /api/books/{ID} -> (GET) Get book by ID!
    findByID: async (id) => {
        return await axiosInstance.get(`/books/${id}`);
    },

    // /api/books/ -> (GET) Get all books!
    findAll: async () => {
        return await axiosInstance.get("/books/");
    },

    // api/books/add-book -> (POST) Add a new book!
    addBook: async (bookData) => {
        return await axiosInstance.post("/books/add-book", bookData);
    },

    // /api/books/edit-book/{ID} -> (PUT) Edit an existing book!
    editBook: async (id, bookData) => {
        return await axiosInstance.put(`/books/edit-book/${id}`, bookData)
    },

    // /api/books/delete-book/{ID} -> (DELETE) Delete a book!
    deleteBook: async (id) => {
        return await axiosInstance.delete(`/books/delete-book/${id}`);
    },
};

export default bookRepository;
