import React, {useState} from 'react';
import {Button, Dialog, DialogActions, DialogContent, DialogTitle, TextField} from "@mui/material";

const EditCountryDialog = ({open, onClose, country, onEdit}) => {

    const [formData, setFormData] = useState({
        "name": country.name,
        "continent": country.continent,
    })

    const handleChange = (event) => {
        const {name, value} = event.target
        setFormData({...formData, [name]: value});
    }

    const handleSubmit = () => {
        onEdit(country.id, formData);
        setFormData(formData);
        onClose();
    }

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Edit Country</DialogTitle>
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
                    label="Continent"
                    name="continent"
                    value={formData.continent}
                    onChange={handleChange}
                    fullWidth
                />
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button onClick={handleSubmit} variant="contained" color="primary">Edit</Button>
            </DialogActions>
        </Dialog>
    );
};

export default EditCountryDialog;