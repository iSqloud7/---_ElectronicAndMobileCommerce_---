import {useEffect, useState} from "react";
import bookRepository from "../repository/bookRepository.js";
import authorRepository from "../repository/authorRepository.js";

const useBookDetails = (id) => {
    const [state, setState] = useState({
        "book": null,
        "author": null,
    });

    useEffect(() => {
        bookRepository.findByID(id)
            .then((response) => {
                setState(prevState => ({
                    ...prevState,
                    "book": response.data,
                }));
                authorRepository.findByID(response.data.authorID)
                    .then((response) => {
                        setState(prevState => ({
                            ...prevState,
                            "author": response.data,
                        }));
                    })
                    .catch((error) => {
                        console.log(error);
                    });
            })
            .catch((error) => {
                console.log(error);
            });
    }, [id]);

    return state;
};

export default useBookDetails;
