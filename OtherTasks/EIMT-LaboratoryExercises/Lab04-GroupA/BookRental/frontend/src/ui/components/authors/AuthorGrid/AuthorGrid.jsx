import {Grid} from "@mui/material";
import AuthorCard from "../AuthorCard/AuthorCard.jsx";
import './AuthorGrid.css';

const AuthorGrid = ({authors, onEdit, onDelete}) => {
    return (
        <Grid
            container
            spacing={{xs: 2, md: 3}}>
            {authors.map((author) => (
                <Grid key={author.id}
                      size={{xs: 12, sm: 6, md: 4, lg: 3}}>
                    <AuthorCard author={author} onEdit={onEdit} onDelete={onDelete}/>
                </Grid>
            ))}
        </Grid>
    );
};

export default AuthorGrid;
