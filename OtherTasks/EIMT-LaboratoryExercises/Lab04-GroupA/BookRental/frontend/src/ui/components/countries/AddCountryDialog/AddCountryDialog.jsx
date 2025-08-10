import {useState} from "react";
import {
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    TextField
} from "@mui/material";

const initialFormData = {
    "name": "",
    "continent": "",
};

const AddCountryDialog = ({open, onClose, onAdd}) => {
    const [formData, setFormData] = useState(initialFormData);

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
            <DialogTitle>Add Country!</DialogTitle>
            <DialogContent>
                {/*===== COUNTRY NAME =====*/}
                <TextField
                    margin="dense"
                    label="Name"
                    name="name"
                    value={formData.name}
                    onChange={handleChange}
                    fullWidth/>
                {/*===== COUNTRY CONTINENT =====*/}
                <TextField
                    margin="dense"
                    label="Continent"
                    name="continent"
                    value={formData.continent}
                    onChange={handleChange}
                    fullWidth/>
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel!</Button>
                <Button onClick={handleSubmit} variant="contained" color="primary">Add Country!</Button>
            </DialogActions>
        </Dialog>
    );
};

export default AddCountryDialog;
