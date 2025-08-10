import {useNavigate, useParams} from "react-router";
import {Box, Breadcrumbs, Link, Button, Chip, CircularProgress, Grid, Stack, Typography, Paper} from "@mui/material";
import useAuthorDetails from "../../../../hooks/useAuthorDetails.js";
import OutlinedFlagIcon from '@mui/icons-material/OutlinedFlag';
import SouthAmericaIcon from '@mui/icons-material/SouthAmerica';
import ArrowBack from '@mui/icons-material/ArrowBack';

const AuthorDetails = () => {
    const {id} = useParams();
    const navigate = useNavigate();
    const {author, country} = useAuthorDetails(id);

    if (!author || !country) {
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
                        navigate("/authors");
                    }}>
                    Authors
                </Link>
                <Typography
                    color="text.primary">
                    {author.name}
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
                                {author.name}
                            </Typography>
                            <Stack
                                direction="row"
                                spacing={1}
                                sx={{mb: 3}}>
                                <Chip
                                    icon={<OutlinedFlagIcon/>}
                                    label={country.name}
                                    color="primary"
                                    variant="outlined"
                                    sx={{p: 2}}/>
                                <Chip
                                    icon={<SouthAmericaIcon/>}
                                    label={country.continent}
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
                            onClick={() => navigate("/authors")}>
                            Back to authors!
                        </Button>
                    </Grid>
                </Grid>
            </Paper>
        </Box>
    );
};

export default AuthorDetails;
