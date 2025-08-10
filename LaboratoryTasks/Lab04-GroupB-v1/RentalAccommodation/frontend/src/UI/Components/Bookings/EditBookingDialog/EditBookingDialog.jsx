import React, {useState} from 'react';
import useHosts from "../../../../Hooks/useHosts.js";
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

// Dialog for editing an existing booking!

const EditBookingDialog = ({open, onClose, booking, onEdit}) => {

    // Initialize form data with the booking's current values!
    const [formData, setFormData] = useState({
        "name": booking.name,
        "numRooms": booking.numRooms,
        "booked": booking.booked,
        "category": booking.category,
        "hostId": booking.hostId,
    });

    // Get hosts data for populating the host dropdown!
    const {hosts, loading} = useHosts()

    // Available booking categories!
    const categories = ["ROOM", "HOUSE", "FLAT", "APARTMENT", "HOTEL", "MOTEL"]

    /**
     * Handles form field changes!
     * @param {Object} event - The input change event!
     */
    const handleChange = (event) => {
        const {name, value} = event.target
        setFormData({...formData, [name]: value});
    }

    /**
     * Handles form submission!
     * Edits the booking and closes the dialog!
     */
    const handleSubmit = () => {
        onEdit(booking.id, formData);
        setFormData(formData)
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
                <Button onClick={handleSubmit} variant="contained" color="primary">Add</Button>
            </DialogActions>
        </Dialog>
    );
};

export default EditBookingDialog;