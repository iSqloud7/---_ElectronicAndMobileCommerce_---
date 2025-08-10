import React from 'react';
import {Grid} from "@mui/material";
import BookingCard from "../BookingCard/BookingCard.jsx";

// Grid component for displaying multiple booking cards!

const BookingGrid = ({bookings, onEdit, onDelete}) => {
    return (
        <Grid container spacing={{xs: 2, md: 3}}>
            {bookings.map((booking) => (
                <Grid key={booking.id} size={{xs: 12, sm: 6, md: 4, lg: 3}}>
                    <BookingCard booking={booking} onEdit={onEdit} onDelete={onDelete}/>
                </Grid>
            ))}
        </Grid>
    );
};

export default BookingGrid;