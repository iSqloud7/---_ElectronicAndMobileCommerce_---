import {
    Button,
    Dialog,
    DialogActions, DialogContent,
    DialogTitle,
    FormControl,
    InputLabel,
    MenuItem,
    Select,
    TextField
} from "@mui/material";
import useCountries from "../../../../hooks/useCountries.js";
import {useState} from "react";

const EditAuthorDialog = ({open, onClose, author, onEdit}) => {
    const [formData, setFormData] = useState({
        "name": author.name,
        "surname": author.surname,
        "countryID": author.countryID,
    });

    const {countries} = useCountries();

    const handleChange = (event) => {
        const {name, value} = event.target;
        setFormData({...formData, [name]: value});
    };

    const handleSubmit = () => {
        onEdit(author.authorID, formData);
        setFormData(formData);
        onClose();
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Edit Author!</DialogTitle>
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
                    label="Name"
                    name="name"
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
                <Button onClick={handleSubmit} variant="contained" color="primary">Edit Author!</Button>
            </DialogActions>
        </Dialog>
    );
};

export default EditAuthorDialog;
