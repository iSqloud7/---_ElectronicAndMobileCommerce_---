import {useEffect, useState} from "react";
import authorRepository from "../repository/authorRepository.js";
import countryRepository from "../repository/countryRepository.js";

const useAuthorDetails = (id) => {
    const [state, setState] = useState({
        "author": null,
        "country": null,
    });

    useEffect(() => {
        authorRepository.findByID(id)
            .then((response) => {
                setState(prevState => ({
                    ...prevState,
                    "author": response.data,
                }));
                countryRepository.findByID(response.data.countryID)
                    .then((response) => {
                        setState(prevState => ({
                            ...prevState,
                            "country": response.data,
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

export default useAuthorDetails;
