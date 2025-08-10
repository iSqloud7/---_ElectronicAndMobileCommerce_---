import React, {useState} from 'react';
import useCountries from "../../../../Hooks/useCountries.js";
import {
    Button, Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    FormControl,
    InputLabel,
    MenuItem,
    Select,
    TextField
} from "@mui/material";

const EditHostDialog = ({open, onEdit, onClose, host}) => {

    const [formData, setFormData] = useState({
        "name": host.name,
        "surname": host.surname,
        "countryId": host.countryId
    })

    const {countries} = useCountries()

    const handleChange = (event) => {
        const {name,value} = event.target;
        setFormData({...formData,[name]:value});
    }

    const handleSubmit=()=>{
        onEdit(host.id,formData)
        setFormData(formData)
        onClose()
    }

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Edit Product</DialogTitle>
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
                    value={formData.surname}
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
                <Button onClick={handleSubmit} variant="contained" color="primary">Edit</Button>
            </DialogActions>
        </Dialog>
    );
};

export default EditHostDialog;