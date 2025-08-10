import {useNavigate} from "react-router";
import {useState} from "react";
import {Box, Button, Card, CardActions, CardContent, Typography} from "@mui/material";
import InfoIcon from '@mui/icons-material/Info';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import EditBookDialog from '../EditBookDialog/EditBookDialog';
import DeleteBookDialog from '../DeleteBookDialog/DeleteBookDialog';
import './BookCard.css';

const BookCard = ({book, onEdit, onDelete}) => {
    const navigate = useNavigate();
    const [editBookDialogOpen, setEditBookDialogOpen] = useState(false);
    const [deleteBookDialogOpen, setDeleteBookDialogOpen] = useState(false);

    return (
        <>
            <Card
                sx={{boxShadow: 3, borderRadius: 2, p: 1}}>
                <CardContent>
                    <Typography
                        variant="h5">
                        {book.name}
                    </Typography>
                </CardContent>
                <CardActions
                    sx={{justifyContent: 3}}>
                    <Button
                        size="small"
                        color="info"
                        startIcon={<InfoIcon/>}
                        sx={{mr: "0.25rem"}}
                        onClick={() => navigate(`/books/${book.bookID}`)}>
                        Info!
                    </Button>
                    <Box>
                        <Button
                            size="small"
                            color="warning"
                            startIcon={<EditIcon/>}
                            sx={{mr: "0.25rem"}}
                            onClick={() => setEditBookDialogOpen(true)}>
                            Edit book!
                        </Button>
                        <Button
                            size="small"
                            color="error"
                            startIcon={<DeleteIcon/>}
                            onClick={() => setDeleteBookDialogOpen(true)}>
                            Delete book!
                        </Button>
                    </Box>
                </CardActions>
            </Card>
            <EditBookDialog
                open={editBookDialogOpen}
                onClose={() => setEditBookDialogOpen(false)}
                book={book}
                onEdit={onEdit}/>
            <DeleteBookDialog
                open={deleteBookDialogOpen}
                onClose={() => setDeleteBookDialogOpen(false)}
                book={book}
                onDelete={onDelete}/>
        </>
    );
};

export default BookCard;
