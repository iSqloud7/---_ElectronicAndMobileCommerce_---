import React, {useState} from 'react';
import useCountries from "../../../../Hooks/useCountries.js";
import {
    Button,
    Dialog, DialogActions,
    DialogContent,
    DialogTitle,
    FormControl,
    InputLabel,
    MenuItem,
    Select,
    TextField
} from "@mui/material";


const initialFormData = {
    "name": "",
    "surname": "",
    "countryId": "",
}

const AddHostDialog = ({open,onClose,onAdd}) => {

    const [formData, setFormData] = useState(initialFormData)
    const {countries} = useCountries()

    const handleChange = (event) => {
        const {name, value} = event.target;
        setFormData({...formData, [name]: value});
    }

    const handleSubmit = () => {
        onAdd(formData);
        setFormData(initialFormData)
        onClose();
    }

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Add Product</DialogTitle>
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
                    label="Surname"
                    name="surname"
                    value={formData.price}
                    onChange={handleChange}
                    fullWidth
                />

                <FormControl fullWidth margin="dense">
                    <InputLabel>Country</InputLabel>
                    <Select
                        name="countryId"
                        value={formData.countryId}
                        onChange={handleChange}
                        label="Country"
                        variant="outlined">
                        {countries.map((c) => (
                            <MenuItem key={c.id} value={c.id}>{c.name}</MenuItem>
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

export default AddHostDialog;