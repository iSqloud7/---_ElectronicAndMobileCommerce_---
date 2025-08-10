import React from 'react';
import {Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle} from "@mui/material";

const DeleteHostDialog = ({open, onClose, onDelete, host}) => {

    const handleSubmit = () => {
        onDelete(host.id);
        onClose()
    }

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Delete Host</DialogTitle>
            <DialogContent>
                <DialogContentText>
                    Are you sure you want to delete <strong>{host.name} {host.surname}</strong>? This action cannot be undone.
                </DialogContentText>
                <DialogActions>
                    <Button onClick={onClose}>Cancel</Button>
                    <Button onClick={handleSubmit} color="error" variant="contained">Delete</Button>
                </DialogActions>
            </DialogContent>
        </Dialog>

    );
};

export default DeleteHostDialog;