import {useCallback, useEffect, useState} from "react";
import countryRepository from "../repository/countryRepository.js";

const initialState = {
    "countries": [],
    "loading": true,
};

const useCountries = () => {
    const [state, setState] = useState(initialState);

    const fetchCountries = useCallback(() => {
        countryRepository.findAll()
            .then((response) => {
                setState({
                    countries: response.data,
                    loading: false,
                });
            })
            .catch((error) => {
                console.log("Failed to fetch countries: ", error)
            });
    }, []);

    const onAdd = useCallback((data) => {
        countryRepository.addCountry(data)
            .then(() => {
                console.log("Successfully added a new country!");
                fetchCountries();
            })
            .catch((error) => {
                console.log(error);
            });
    }, [fetchCountries]);

    const onEdit = useCallback((id, data) => {
        countryRepository.editCountry(id, data)
            .then(() => {
                console.log(`Successfully edited the country with ID: ${id}!`);
                fetchCountries();
            })
            .catch((error) => {
                console.log(error);
            });
    }, [fetchCountries]);

    const onDelete = useCallback((id) => {
        countryRepository.deleteCountry(id)
            .then(() => {
                console.log(`Successfully deleted the country with ID: ${id}!`);
                fetchCountries();
            })
            .catch((error) => {
                console.log(error);
            })
    }, [fetchCountries]);

    useEffect(() => {
        fetchCountries()
    }, [fetchCountries]);

    return {
        ...state,
        onAdd: onAdd,
        onEdit: onEdit,
        onDelete: onDelete
    }
};

export default useCountries;
