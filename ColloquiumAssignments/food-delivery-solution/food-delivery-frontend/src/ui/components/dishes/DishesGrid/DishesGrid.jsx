import React from 'react';
import {Grid} from "@mui/material";
import DishCard from "../DishCard/DishCard.jsx";

const DishesGrid = ({dishes, onEdit, onDelete}) => {
    return (
        <Grid container spacing={{xs: 2, md: 3}}>
            {dishes.map((dish) => (
                <Grid key={dish.id} size={{xs: 12, sm: 6, md: 4, lg: 3}} display="flex">
                    <DishCard dish={dish} onEdit={onEdit} onDelete={onDelete}/>
                </Grid>
            ))}
        </Grid>
    );
};

export default DishesGrid;