import {useCallback, useEffect, useState} from "react";
import hostRepository from "../Repository/hostRepository.js";

// Hook for host state management!

/**
 * Initial state for the hosts hook!
 * Contains empty hosts array and loading flag!
 */
const initialState = {
    "hosts": [],
    "loading": true,
}

/**
 * Custom hook for managing host data and operations!
 * Provides CRUD operations and state management for hosts!
 */
const useHosts = () => {

    // Initialize state with initial values!
    const [state, setState] = useState(initialState)

    /**
     * Fetches all hosts from the API
     * Updates state with fetched data
     */
    const fetchHosts = useCallback(() => {
        // Reset state to initial loading state!
        setState(initialState)

        // Update state with fetched hosts and set loading to false!
        hostRepository.findAll()
            .then((response) => {
                setState({
                    "hosts": response.data,
                    "loading": false,
                });
            })
            .catch((error) => {
                console.log(error)
            })
    }, [])

    /**
     * Adds a new host!
     * @param {Object} data - The host data to add!
     */
    const onAdd = useCallback((data) => {
        hostRepository.add(data)
            .then(() => {
                console.log("Successfully added a Host!")
                fetchHosts()
            })
            .catch((error) => {
                console.log(error)
            })
    }, [fetchHosts])

    /**
     * Edits an existing host!
     * @param {string|number} id - The ID of the host to edit!
     * @param {Object} data - The updated host data!
     */
    const onEdit = useCallback((id, data) => {
        hostRepository.edit(id, data)
            .then(() => {
                console.log("Successfully edited host!")
                fetchHosts()
            })
            .catch((error) => {
                console.log(error)
            })
    }, [fetchHosts])

    /**
     * Deletes a host!
     * @param {string|number} id - The ID of the host to delete!
     */
    const onDelete = useCallback((id) => {
        hostRepository.delete(id)
            .then(() => {
                console.log("Successfully deleted a host!")
                fetchHosts()
            })
            .catch((error) => {
                console.log(error)
            })
    }, [fetchHosts])

    // Fetch hosts when component mounts!
    useEffect(() => {
        fetchHosts()
    }, [fetchHosts]);

    // Return state and CRUD operations!
    return {...state, onAdd: onAdd, onDelete: onDelete, onEdit: onEdit};
};

export default useHosts;