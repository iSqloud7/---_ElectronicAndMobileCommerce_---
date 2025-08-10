import {Grid} from "@mui/material";
import CountryCard from "../CountryCard/CountryCard.jsx";
import './CountryGrid.css';

const CountryGrid = ({countries, onEdit, onDelete}) => {
    return (
        <Grid
            container
            spacing={{xs: 2, md: 3}}>
            {countries.map((country) => (
                <Grid item key={country.id}
                      size={{xs: 12, sm: 6, md: 4, lg: 3}}>
                    <CountryCard country={country} onEdit={onEdit} onDelete={onDelete}/>
                </Grid>
            ))}
        </Grid>
    );
};

export default CountryGrid;
