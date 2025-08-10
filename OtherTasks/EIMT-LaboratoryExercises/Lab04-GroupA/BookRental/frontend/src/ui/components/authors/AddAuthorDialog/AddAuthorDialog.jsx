import {useState} from "react";
import useCountries from "../../../../hooks/useCountries.js";
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

const initialFormData = {
    "name": "",
    "surname": "",
    "countryID": "",
};

const AddAuthorDialog = ({open, onClose, onAdd}) => {
    const [formData, setFormData] = useState(initialFormData);

    const {countries} = useCountries();

    const handleChange = (event) => {
        // name -> which input field is being changed
        // value -> the new value for the input field
        const {name, value} = event.target;
        setFormData({...formData, [name]: value});
    };

    const handleSubmit = () => {
        onAdd(formData);
        setFormData(initialFormData);
        onClose();
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Add Author!</DialogTitle>
            <DialogContent>
                {/*===== AUTHOR NAME =====*/}
                <TextField
                    margin="dense"
                    label="Name"
                    name="name"
                    value={formData.name}
                    onChange={handleChange}
                    fullWidth/>
                {/*===== AUTHOR SURNAME =====*/}
                <TextField
                    margin="dense"
                    label="Surname"
                    name="surname"
                    value={formData.surname}
                    onChange={handleChange}
                    fullWidth/>
                {/*===== AUTHOR COUNTRIES =====*/}
                <FormControl
                    margin="dense"
                    fullWidth>
                    <InputLabel>Country</InputLabel>
                    <Select
                        name="countryID"
                        value={formData.countryID}
                        onChange={handleChange}
                        label="Country"
                        variant="outlined">
                        {countries.map((country) => (
                            <MenuItem key={country.countryID} value={country.countryID}>
                                {country.name}
                            </MenuItem>
                        ))}
                    </Select>
                </FormControl>
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel!</Button>
                <Button onClick={handleSubmit} variant="contained" color="primary">Add Author!</Button>
            </DialogActions>
        </Dialog>
    );
};

export default AddAuthorDialog;
