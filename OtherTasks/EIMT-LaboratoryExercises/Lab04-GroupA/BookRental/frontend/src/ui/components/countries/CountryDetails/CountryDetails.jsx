import {useNavigate, useParams} from "react-router";
import {Box, Breadcrumbs, Link, Button, CircularProgress, Grid, Paper, Typography} from "@mui/material";
import useCountryDetails from "../../../../hooks/useCountryDetails.js";

const CountryDetails = () => {
    const {id} = useParams();
    const navigate = useNavigate();
    const {country} = useCountryDetails(id);

    if (!country) {
        return (
            <Box sx={{display: "flex", justifyContent: "center", alignItems: "center", minHeight: "60vh"}}>
                <CircularProgress/>
            </Box>
        );
    }

    return (
        <Box>
            <Breadcrumbs aria-label="breadcrumb" sx={{mb: 3}}>
                <Link
                    underline="hover"
                    color="inherit"
                    href="#"
                    onClick={(e) => {
                        e.preventDefault();
                        navigate("/countries");
                    }}>
                    Countries
                </Link>
                <Typography color="text.primary">{country.name}</Typography>
            </Breadcrumbs>
            <Paper elevation={2} sx={{p: 4, borderRadius: 4}}>
                <Grid container spacing={2}>
                    <Grid item xs={12}>
                        <Typography variant="h4" gutterBottom sx={{fontWeight: 600}}>
                            {country.name}
                        </Typography>
                        <Typography variant="subtitle1" gutterBottom>
                            Continent: {country.continent}
                        </Typography>
                    </Grid>
                    <Grid item xs={12}>
                        <Button
                            variant="outlined"
                            onClick={() => navigate("/countries")}>
                            Back to countries!
                        </Button>
                    </Grid>
                </Grid>
            </Paper>
        </Box>
    );
};

export default CountryDetails;
