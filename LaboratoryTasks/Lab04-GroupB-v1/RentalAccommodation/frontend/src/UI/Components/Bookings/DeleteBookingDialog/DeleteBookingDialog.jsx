import React from 'react';
import {Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle} from "@mui/material";

// Dialog for confirming booking deletion!

const DeleteBookingDialog = ({open, onClose, booking, onDelete}) => {

    /**
     * Handles form submission!
     * Deletes the booking and closes the dialog!
     */
    const handleSubmit = () => {
        onDelete(booking.id);
        onClose();
    }

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Delete Booking</DialogTitle>
            <DialogContent>
                <DialogContentText>
                    Are you sure you want to delete <strong>{booking.name}</strong>? This action cannot be undone.
                </DialogContentText>
                <DialogActions>
                    <Button onClick={onClose}>Cancel</Button>
                    <Button onClick={handleSubmit} color="error" variant="contained">Delete</Button>
                </DialogActions>
            </DialogContent>
        </Dialog>

    );
};

export default DeleteBookingDialog;