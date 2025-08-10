import useBooks from "../../../hooks/useBooks.js";
import {Box, Button, CircularProgress} from "@mui/material";
import AddBookDialog from "../../components/books/AddBookDialog/AddBookDialog.jsx";
import {useState} from "react";
import BookGrid from "../../components/books/BooksGrid/BookGrid.jsx";

const BookPage = () => {
    const {books, loading, onAdd, onEdit, onDelete} = useBooks();
    const [addBookDialogOpen, setAddBookDialogOpen] = useState(false);

    return (
        <>
            <Box
                className={"books-box"}>
                {loading && (
                    <Box
                        className="progress-box">
                        <CircularProgress/>
                    </Box>
                )}
                {!loading &&
                    <>
                        <Box
                            sx={{display: "flex", justifyContent: "flex-end", mb: 2}}>
                            <Button
                                variant="contained"
                                color="primary"
                                onClick={() => setAddBookDialogOpen(true)}>
                                Add Book!
                            </Button>
                        </Box>
                        <BookGrid books={books} onEdit={onEdit} onDelete={onDelete}/>
                    </>}
            </Box>
            <AddBookDialog
                open={addBookDialogOpen}
                onClose={() => setAddBookDialogOpen(false)}
                onAdd={onAdd}/>
        </>
    );
};

export default BookPage;
