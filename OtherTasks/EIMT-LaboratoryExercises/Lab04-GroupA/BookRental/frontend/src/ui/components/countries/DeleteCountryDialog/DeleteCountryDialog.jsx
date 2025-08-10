import {Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle} from "@mui/material";

const DeleteCountryDialog = ({open, onClose, country, onDelete}) => {

    const handleSubmit = () => {
        onDelete(country.countryID);
        onClose();
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Delete country!</DialogTitle>
            <DialogContent>
                <DialogContentText>
                    Are you sure you want to delete <strong>{country.name}</strong>? This action cannot be undone.
                </DialogContentText>
                <DialogActions>
                    <Button onClick={onClose}>Cancel!</Button>
                    <Button onClick={handleSubmit} color="error" variant="contained">Delete country!</Button>
                </DialogActions>
            </DialogContent>
        </Dialog>
    );
};

export default DeleteCountryDialog;
