import {useNavigate, useParams} from "react-router";
import {Box, Breadcrumbs, Link, Button, Chip, CircularProgress, Grid, Stack, Typography, Paper} from "@mui/material";
import useBookDetails from "../../../../hooks/useBookDetails.js";
import CategoryIcon from '@mui/icons-material/Category';
import PersonIcon from '@mui/icons-material/Person';
import ArrowBack from '@mui/icons-material/ArrowBack';

const BookDetails = () => {
    const {id} = useParams();
    const navigate = useNavigate();
    const {book, author} = useBookDetails(id);

    if (!book || !author) {
        return (
            <Box
                sx={{display: "flex", justifyContent: "center", alignItems: "center", minHeight: "60vh"}}>
                <CircularProgress/>
            </Box>
        );
    }

    return (
        <Box>
            <Breadcrumbs
                aria-label="breadcrumb"
                sx={{mb: 3}}>
                <Link
                    underline="hover"
                    color="inherit"
                    href="#"
                    onClick={(e) => {
                        e.preventDefault();
                        navigate("/books");
                    }}>
                    Books
                </Link>
                <Typography
                    color="text.primary">
                    {book.name}
                </Typography>
            </Breadcrumbs>
            <Paper
                elevation={2}
                sx={{p: 4, borderRadius: 4}}>
                <Grid
                    container spacing={4}>
                    <Grid
                        size={{xs: 12, md: 6}}>
                        <Box
                            sx={{bg: 3}}>
                            <Typography
                                variant="h3"
                                gutterBottom
                                sx={{fontWeight: 600}}>
                                {book.name}
                            </Typography>
                            <Stack
                                direction="row"
                                spacing={1}
                                sx={{mb: 3}}>
                                <Chip
                                    icon={<CategoryIcon/>}
                                    label={book.category}
                                    color="primary"
                                    variant="outlined"
                                    sx={{p: 2}}/>
                                <Chip
                                    icon={<PersonIcon/>}
                                    label={author.name}
                                    color="secondary"
                                    variant="outlined"
                                    sx={{p: 2}}/>
                            </Stack>
                        </Box>
                    </Grid>
                    <Grid
                        size={12}
                        display="flex"
                        justifyContent="space-between">
                        <Button
                            variant="outlined"
                            startIcon={<ArrowBack/>}
                            onClick={() => navigate("/books")}>
                            Back to books!
                        </Button>
                    </Grid>
                </Grid>
            </Paper>
        </Box>
    );
};

export default BookDetails;
