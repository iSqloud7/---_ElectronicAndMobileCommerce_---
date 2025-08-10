import React, {useState} from 'react';
import {
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    FormControl,
    InputLabel,
    MenuItem,
    Select,
    TextField
} from "@mui/material";
import useHosts from "../../../../Hooks/useHosts.js";

// Dialog for adding a new booking!

/**
 * Initial form data for adding a booking!
 * Contains empty values for all required fields!
 */
const initialFormData = {
    "name": "",
    "numRooms": "",
    "category": "",
    "hostId": "",
}

const AddBookingDialog = ({open, onClose, onAdd}) => {

    // State for form data!
    const [formData, setFormData] = useState(initialFormData)

    // Available booking categories!
    const categories = ["ROOM", "HOUSE", "FLAT", "APARTMENT", "HOTEL", "MOTEL"]

    // Get hosts data for populating the host dropdown!
    const {hosts, loading} = useHosts()

    /**
     * Handles form field changes!
     * @param {Object} event - The input change event!
     */
    const handleChange = (event) => {
        const {name, value} = event.target;
        setFormData({...formData, [name]: value});
    }

    /**
     * Handles form submission!
     * Adds a new booking and closes the dialog!
     */
    const handleSubmit = () => {
        onAdd(formData);
        setFormData(initialFormData)
        onClose();
    }

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Add Booking</DialogTitle>
            <DialogContent>
                <TextField
                    margin="dense"
                    label="Name"
                    name="name"
                    value={formData.name}
                    onChange={handleChange}
                    fullWidth
                />
                <TextField
                    margin="dense"
                    label="numRooms"
                    name="numRooms"
                    type="number"
                    value={formData.numRooms}
                    onChange={handleChange}
                    fullWidth
                />
                <FormControl fullWidth margin="dense">
                    <InputLabel>Host</InputLabel>
                    <Select
                        name="hostId"
                        value={formData.hostId}
                        onChange={handleChange}
                        label="Host"
                        variant="outlined">
                        {hosts.map((host) => (
                            <MenuItem key={host.id} value={host.id}>{host.name}</MenuItem>
                        ))}
                    </Select>
                </FormControl>
                <FormControl fullWidth margin="dense">
                    <InputLabel>Category</InputLabel>
                    <Select
                        name="category"
                        value={formData.category}
                        onChange={handleChange}
                        label="Category"
                        variant="outlined">
                        {categories.map((category) => (
                            <MenuItem key={category} value={category}>{category}</MenuItem>
                        ))}
                    </Select>
                </FormControl>
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button onClick={handleSubmit} variant="contained" color="primary">Add!</Button>
            </DialogActions>
        </Dialog>
    );
};

export default AddBookingDialog;