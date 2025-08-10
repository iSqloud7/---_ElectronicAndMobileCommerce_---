import {useEffect, useState} from "react";
import countryRepository from "../repository/countryRepository.js";

const useCountryDetails = (id) => {
    const [state, setState] = useState({
        "country": null,
    });

    useEffect(() => {
        countryRepository.findByID(id)
            .then((response) => {
                setState(prevState => ({
                    ...prevState,
                    "country": response.data,
                }));
            })
            .catch((error) => {
                console.log(error);
            });
    }, [id]);

    return state;
};

export default useCountryDetails;
