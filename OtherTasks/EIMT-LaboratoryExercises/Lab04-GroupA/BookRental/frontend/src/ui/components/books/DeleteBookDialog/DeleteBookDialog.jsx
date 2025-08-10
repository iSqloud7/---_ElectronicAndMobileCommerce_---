import {Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle} from "@mui/material";

const DeleteBookDialog = ({open, onClose, book, onDelete}) => {

    const handleSubmit = () => {
        onDelete(book.bookID);
        onClose();
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Delete book!</DialogTitle>
            <DialogContent>
                <DialogContentText>
                    Are you sure you want to delete <strong>{book.name}</strong>? This action cannot be undone.
                </DialogContentText>
                <DialogActions>
                    <Button onClick={onClose}>Cancel!</Button>
                    <Button onClick={handleSubmit} color="error" variant="contained">Delete book!</Button>
                </DialogActions>
            </DialogContent>
        </Dialog>
    );
};

export default DeleteBookDialog;
