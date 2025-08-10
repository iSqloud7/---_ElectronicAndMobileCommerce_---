import {useCallback, useEffect, useState} from "react";
import bookingRepository from "../Repository/bookingRepository.js";

// Hook for booking state management!

/**
 * Initial state for the bookings hook!
 * Contains empty bookings array and loading flag!
 */
const initialState = {
    "bookings": [],
    "loading": true
};

/**
 * Custom hook for managing booking data and operations
 * Provides CRUD operations and state management for bookings
 */
const useBookings = () => {

    // Initialize state with initial values!
    const [state, setState] = useState(initialState)

    /**
     * Fetches all bookings from the API!
     * Updates state with fetched data!
     */
    const fetchBookings = useCallback(() => {
        // Reset state to initial loading state!
        setState(initialState);

        // Update state with fetched bookings and set loading to false!
        bookingRepository.findAll()
            .then((response) => {
                setState({
                    "bookings": response.data,
                    "loading": false,
                });
            })
            .catch((error) => {
                console.log(error)
            })

    }, [])

    /**
     * Adds a new booking!
     * @param {Object} data - The booking data to add!
     */
    const onAdd = useCallback((data) => {
        bookingRepository.add(data)
            .then((response) => {
                console.log("Successfully added a new Booking!")
                fetchBookings();
            })
            .catch((error) => {
                console.log(error)
            })
    }, [fetchBookings])

    /**
     * Edits an existing booking!
     * @param {string|number} id - The ID of the booking to edit!
     * @param {Object} data - The updated booking data!
     */
    const onEdit = useCallback((id, data) => {
        bookingRepository.edit(id, data)
            .then((response) => {
                console.log(`Booking with ID:${id} was edited!`)
                fetchBookings()
            })
            .catch((error) => {
                console.log(error)
            })
    }, [fetchBookings])

    /**
     * Deletes a booking!
     * @param {string|number} id - The ID of the booking to delete!
     */
    const onDelete = useCallback((id) => {
        bookingRepository.delete(id)
            .then((response) => {
                console.log(`Booking with ID: ${id} was deleted!`)
                fetchBookings();
            })
            .catch((error) => {
                console.log(error)
            })
    }, [fetchBookings])

    // Fetch bookings when component mounts!
    useEffect(() => {
        fetchBookings();
    }, [fetchBookings]);

    // Return state and CRUD operations!
    return {...state, onAdd: onAdd, onEdit: onEdit, onDelete: onDelete}
};

export default useBookings;