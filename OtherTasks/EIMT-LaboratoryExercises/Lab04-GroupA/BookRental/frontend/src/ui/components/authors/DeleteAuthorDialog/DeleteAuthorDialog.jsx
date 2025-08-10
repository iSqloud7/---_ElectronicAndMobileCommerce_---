import {Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle} from "@mui/material";

const DeleteAuthorDialog = ({open, onClose, author, onDelete}) => {

    const handleSubmit = () => {
        onDelete(author.authorID);
        onClose();
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Delete author!</DialogTitle>
            <DialogContent>
                <DialogContentText>
                    Are you sure you want to delete <strong>{author.name}</strong>? This action cannot be undone.
                </DialogContentText>
                <DialogActions>
                    <Button onClick={onClose}>Cancel!</Button>
                    <Button onClick={handleSubmit} color="error" variant="contained">Delete author!</Button>
                </DialogActions>
            </DialogContent>
        </Dialog>
    );
};

export default DeleteAuthorDialog;
