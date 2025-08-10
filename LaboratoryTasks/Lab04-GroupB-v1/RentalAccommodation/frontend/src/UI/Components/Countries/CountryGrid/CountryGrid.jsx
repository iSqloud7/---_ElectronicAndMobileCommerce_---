import React from 'react';
import {Grid} from "@mui/material";
import CountryCard from "../CountryCard/CountryCard.jsx";

const CountryGrid = ({countries,onDelete,onEdit}) => {
    return (
        <Grid container spacing={{xs: 2, md: 3}}>
            {countries.map((country) => (
                <Grid key={country.id} size={{xs: 12, sm: 6, md: 4, lg: 3}}>
                    <CountryCard country={country} onDelete={onDelete} onEdit={onEdit}/>
                </Grid>
            ))}
        </Grid>

    );
};

export default CountryGrid;