import {useNavigate} from "react-router";
import {useState} from "react";
import {Box, Button, Card, CardActions, CardContent, Typography} from "@mui/material";
import InfoIcon from '@mui/icons-material/Info';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import EditAuthorDialog from '../EditAuthorDialog/EditAuthorDialog';
import DeleteAuthorDialog from "../DeleteAuthorDialog/DeleteAuthorDialog";
import './AuthorCard.css';

const AuthorCard = ({author, onEdit, onDelete}) => {
    const navigate = useNavigate();
    const [editAuthorDialogOpen, setEditAuthorDialogOpen] = useState(false);
    const [deleteAuthorDialogOpen, setDeleteAuthorDialogOpen] = useState(false);

    return (
        <>
            <Card
                sx={{boxShadow: 3, borderRadius: 2, p: 1}}>
                <CardContent>
                    <Typography
                        variant="h5">
                        {author.name}
                    </Typography>
                </CardContent>
                <CardActions
                    sx={{justifyContent: 3,}}>
                    <Button
                        size="small"
                        color="info"
                        startIcon={<InfoIcon/>}
                        sx={{mr: "0.25rem"}}
                        onClick={() => navigate(`/authors/${author.authorID}`)}>
                        Info!
                    </Button>
                    <Box>
                        <Button
                            size="small"
                            color="warning"
                            startIcon={<EditIcon/>}
                            sx={{mr: "0.25rem"}}
                            onClick={() => setEditAuthorDialogOpen(true)}>
                            Edit author!
                        </Button>
                        <Button
                            size="small"
                            color="error"
                            startIcon={<DeleteIcon/>}
                            onClick={() => setDeleteAuthorDialogOpen(true)}>
                            Delete author!
                        </Button>
                    </Box>
                </CardActions>
            </Card>
            <EditAuthorDialog
                open={editAuthorDialogOpen}
                onClose={() => setEditAuthorDialogOpen(false)}
                author={author}
                onEdit={onEdit}/>
            <DeleteAuthorDialog
                open={deleteAuthorDialogOpen}
                onClose={() => setDeleteAuthorDialogOpen(false)}
                author={author}
                onDelete={onDelete}/>
        </>
    );
};

export default AuthorCard;
