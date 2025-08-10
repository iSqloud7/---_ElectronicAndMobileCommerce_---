import {useCallback, useEffect, useState} from "react";
import countryRepository from "../Repository/countryRepository.js";

// Hook for country state management!

/**
 * Initial state for the countries hook!
 * Contains empty countries array and loading flag!
 */
const initialState = {
    "countries": [],
    "loading": true,
};

/**
 * Custom hook for managing country data and operations
 * Provides CRUD operations and state management for countries
 */
const useCountries = () => {

    // Initialize state with initial values!
    const [state, setState] = useState(initialState)

    /**
     * Fetches all countries from the API!
     * Updates state with fetched data!
     */
    const fetchCountries = useCallback(() => {
        // Reset state to initial loading state!
        setState(initialState)

        // Update state with fetched countries and set loading to false!
        countryRepository.findAll()
            .then((response) => {
                setState({
                    "countries": response.data,
                    "loading": false
                });
            })
            .catch((error) => {
                console.log(error)
            });
    }, [])

    /**
     * Adds a new country!
     * @param {Object} data - The country data to add!
     */
    const onAdd = useCallback((data) => {
        countryRepository.add(data)
            .then(() => {
                console.log("Successfully added a new Country!")
                fetchCountries()
            })
            .catch((error) => {
                console.log(error)
            })
    }, [fetchCountries])

    /**
     * Edits an existing country!
     * @param {string|number} id - The ID of the country to edit!
     * @param {Object} data - The updated country data!
     */
    const onEdit = useCallback((id, data) => {
        countryRepository.edit(id, data)
            .then(() => {
                console.log("Successfully edited!")
                fetchCountries()
            })
            .catch((error) => {
                console.log(error)
            })
    }, [fetchCountries])

    /**
     * Deletes a country!
     * @param {string|number} id - The ID of the country to delete!
     */
    const onDelete = useCallback((id) => {
        countryRepository.delete(id)
            .then(() => {
                console.log("Successfully deleted a country!")
                fetchCountries()
            })
            .catch((error) => {
                console.log(error)
            })
    }, [fetchCountries])

    /**
     * Finds a country by ID!
     * @param {string|number} id - The ID of the country to find!
     */
    const findById = useCallback((id) => {
        countryRepository.findById(id)
            .then(() => {
                console.log("FoundById!")
                fetchCountries()
            })
            .catch((error) => {
                console.log(error)
            })
    }, [fetchCountries])

    // Fetch countries when component mounts!
    useEffect(() => {
        fetchCountries()
    }, [fetchCountries]);

    // Return state and CRUD operations!
    return {...state, onAdd: onAdd, onDelete: onDelete, onEdit: onEdit, findById: findById}
};

export default useCountries;