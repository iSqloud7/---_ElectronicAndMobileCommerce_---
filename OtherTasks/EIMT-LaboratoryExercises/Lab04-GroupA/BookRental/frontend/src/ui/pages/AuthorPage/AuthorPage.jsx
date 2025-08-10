import useAuthors from "../../../hooks/useAuthors.js";
import {Box, Button, CircularProgress} from "@mui/material";
import AddAuthorDialog from "../../components/authors/AddAuthorDialog/AddAuthorDialog.jsx";
import {useState} from "react";
import BookGrid from "../../components/books/BooksGrid/BookGrid.jsx";
import AuthorGrid from "../../components/authors/AuthorGrid/AuthorGrid.jsx";

const AuthorPage = () => {
    const {authors, loading, onAdd, onEdit, onDelete} = useAuthors();
    const [addAuthorDialogOpen, setAddAuthorDialogOpen] = useState(false);

    return (
        <>
            <Box
                className={"authors-box"}>
                {loading && (
                    <Box
                        className={"progress-box"}>
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
                                onClick={() => setAddAuthorDialogOpen(true)}>
                                Add Author!
                            </Button>
                        </Box>
                        <AuthorGrid authors={authors} onEdit={onEdit} onDelete={onDelete}/>
                    </>}
            </Box>
            <AddAuthorDialog
                open={addAuthorDialogOpen}
                onClose={() => setAddAuthorDialogOpen(false)}
                onAdd={onAdd}/>
        </>
    );
};

export default AuthorPage;
