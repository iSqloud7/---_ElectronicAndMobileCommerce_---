import React, {useState} from 'react';
import {
    Button, Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    FormControl,
    InputLabel,
    Select,
    TextField
} from "@mui/material";

const initialFormData = {
    "name": "",
    "continent": "",
}

const AddCountryDialog = ({open, onClose, onAdd}) => {

    const [formData, setFormData] = useState(initialFormData);

    const handleChange = (event) => {
        const {name, value} = event.target

        setFormData({...formData, [name]: value});
    }

    const handleSubmit = () => {
        onAdd(formData)
        setFormData(initialFormData)
        onClose()
    }

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Add Country</DialogTitle>
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
                    label="Price"
                    name="continent"
                    value={formData.continent}
                    onChange={handleChange}
                    fullWidth
                />
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button onClick={handleSubmit} variant="contained" color="primary">Add</Button>
            </DialogActions>
        </Dialog>

    );
};

export default AddCountryDialog;