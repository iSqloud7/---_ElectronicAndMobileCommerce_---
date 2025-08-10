import bookRepository from "../repository/bookRepository.js";
import {useCallback, useEffect, useState} from "react";

const initialState = {
    "books": [],
    "loading": true,
};

// FUNCTION OBJECT
const useBooks = () => {
    const [state, setState] = useState(initialState);

    const fetchBooks = useCallback(() => {
        bookRepository.findAll()
            .then((response) => {
                setState({
                    books: response.data,
                    loading: false,
                });
            })
            .catch((error) => {
                console.log("Failed to fetch books: ", error);
            });
    }, []);

    const onAdd = useCallback((data) => {
        bookRepository.addBook(data)
            .then(() => {
                console.log("Successfully added a new book!");
                fetchBooks();
            })
            .catch((error) => {
                console.log(error);
            });
    }, [fetchBooks]);

    const onEdit = useCallback((id, data) => {
        bookRepository.editBook(id, data)
            .then(() => {
                console.log(`Successfully edited the book with ID: ${id}!`)
                fetchBooks();
            })
            .catch((error) => {
                console.log(error);
            });
    }, [fetchBooks]);

    const onDelete = useCallback((id) => {
        bookRepository.deleteBook(id)
            .then(() => {
                console.log(`Successfully deleted the book with ID: ${id}!`);
                fetchBooks();
            })
            .catch((error) => {
                console.log(error)
            });
    }, [fetchBooks]);

    useEffect(() => {
        fetchBooks();
    }, [fetchBooks]);

    return {
        ...state,
        onAdd: onAdd,
        onEdit: onEdit,
        onDelete: onDelete
    }
};

export default useBooks;
