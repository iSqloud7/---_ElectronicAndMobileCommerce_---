import {useCallback, useEffect, useState} from "react";
import authorRepository from "../repository/authorRepository.js";

const initialState = {
    "authors": [],
    "loading": true,
};

const useAuthors = () => {
    const [state, setState] = useState(initialState);

    const fetchAuthors = useCallback(() => {
        authorRepository.findAll()
            .then((response) => {
                setState({
                    authors: response.data,
                    loading: false,
                });
            })
            .catch((error) => {
                console.log("Failed to fetch authors: ", error)
            });
    }, []);

    const onAdd = useCallback((data) => {
        authorRepository.addAuthor(data)
            .then(() => {
                console.log("Successfully added a new author!");
                fetchAuthors();
            })
            .catch((error) => {
                console.log(error);
            });
    }, [fetchAuthors]);

    const onEdit = useCallback((id, data) => {
        authorRepository.editAuthor(id, data)
            .then(() => {
                console.log(`Successfully edited the author with ID: ${id}!`);
                fetchAuthors();
            })
            .catch((error) => {
                console.log(error);
            });
    }, [fetchAuthors]);

    const onDelete = useCallback((id) => {
        authorRepository.deleteAuthor(id)
            .then(() => {
                console.log(`Successfully deleted the author with ID: ${id}!`);
                fetchAuthors();
            })
            .catch((error) => {
                console.log(error);
            });
    }, [fetchAuthors]);

    useEffect(() => {
        fetchAuthors();
    }, [fetchAuthors]);

    return {
        ...state,
        onAdd: onAdd,
        onEdit: onEdit,
        onDelete: onDelete
    };
};

export default useAuthors;
