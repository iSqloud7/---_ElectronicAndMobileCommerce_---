import {useState} from "react";
import useAuthors from "../../../../hooks/useAuthors.js";
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
    "category": "",
    "authorID": "",
    "availableCopies": "",
};

const AddBookDialog = ({open, onClose, onAdd}) => {
    const [formData, setFormData] = useState(initialFormData);

    // const authors = useAuthors(); // whole object -> return {...state(authors&loading),onAdd: onAdd,onEdit: onEdit,onDelete: onDelete}
    const {authors} = useAuthors(); // only the authors
    const categories = [
        "NOVEL", "THRILER", "HISTORY", "FANTASY", "BIOGRAPHY", "CLASSICS", "DRAMA"
    ];

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
            <DialogTitle>Add Book!</DialogTitle>
            <DialogContent>
                {/*===== BOOK NAME =====*/}
                <TextField
                    margin="dense"
                    label="Name"
                    name="name"
                    value={formData.name}
                    onChange={handleChange}
                    fullWidth/>
                {/*===== BOOK CATEGORY =====*/}
                <FormControl
                    margin="dense"
                    fullWidth>
                    <InputLabel>Category</InputLabel>
                    <Select
                        name="category"
                        value={formData.category}
                        onChange={handleChange}
                        label="Category"
                        variant="outlined">
                        {categories.map((category) => (
                            <MenuItem key={category} value={category}>
                                {category}
                            </MenuItem>
                        ))}
                    </Select>
                </FormControl>
                {/*===== BOOK AUTHORS =====*/}
                <FormControl
                    margin="dense"
                    fullWidth>
                    <InputLabel>Author</InputLabel>
                    <Select
                        name="authorID"
                        value={formData.authorID}
                        onChange={handleChange}
                        label="Author"
                        variant="outlined">
                        {authors.map((author) => (
                            <MenuItem key={author.authorID} value={author.authorID}>
                                {author.name}
                            </MenuItem>
                        ))}
                    </Select>
                </FormControl>
                {/*===== BOOK AVAILABLE COPIES =====*/}
                <TextField
                    margin="dense"
                    label="Number of available copies"
                    name="availableCopies"
                    type="number"
                    value={formData.availableCopies}
                    onChange={handleChange}
                    fullWidth/>
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel!</Button>
                <Button onClick={handleSubmit} variant="contained" color="primary">Add Book!</Button>
            </DialogActions>
        </Dialog>
    );
};

export default AddBookDialog;
