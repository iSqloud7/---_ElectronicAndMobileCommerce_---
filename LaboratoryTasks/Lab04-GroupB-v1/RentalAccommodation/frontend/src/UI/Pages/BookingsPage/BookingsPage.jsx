import React, {useState} from 'react';
import {Box, Button, CircularProgress, ToggleButton, ToggleButtonGroup} from "@mui/material";
import useBookings from "../../../Hooks/useBOokings.js";
import BookingsGrid from "../../Components/Bookings/BookingGrid/BookingGrid.jsx";
import AddBookingDialog from "../../Components/Bookings/AddBookingDialog/AddBookingDialog.jsx";

const BookingsPage = () => {

    const {bookings, loading, onAdd, onEdit, onDelete} = useBookings()

    const [addBookingDialogOpen, setAddBookingDialogOpen] = useState(false)

    const [categoryFilter, setCategoryFilter] = useState('all');

    const handleCategoryChange = (event, newCategory) => {
        if (newCategory !== null) {
            setCategoryFilter(newCategory);
        }
    }

    const filteredBookings = categoryFilter === 'all'
        ? bookings
        : bookings.filter(acc => acc.category.toLowerCase() === categoryFilter)

    return (
        <>
            <Box className="products-box">
                {loading && (
                    <Box className="progress-box">
                        <CircularProgress/>
                    </Box>
                )}
                {!loading &&
                    <>
                        <Box sx={{display: "flex", justifyContent: "flex-end", mb: 2}}>
                            <Button variant="contained" color="primary" onClick={() => setAddBookingDialogOpen(true)}>
                                Add Booking
                            </Button>
                        </Box>
                        <BookingsGrid bookings={bookings} onEdit={onEdit} onDelete={onDelete}/>
                    </>}
            </Box>
            <Box sx={{
                display: 'flex',
                justifyContent: 'space-between',
                mb: 2,
                alignItems: 'center',
                flexWrap: 'wrap',
                gap: 1
            }}>
                <ToggleButtonGroup
                    color="primary"
                    value={categoryFilter}
                    exclusive
                    onChange={handleCategoryChange}
                    aria-label="Filter by category"
                    size="small"
                    sx={{flexWrap: 'wrap', gap: 1}}
                >
                    <ToggleButton value="all">All</ToggleButton>
                    <ToggleButton value="room">Room</ToggleButton>
                    <ToggleButton value="house">House</ToggleButton>
                    <ToggleButton value="flat">Flat</ToggleButton>
                    <ToggleButton value="apartment">Apartment</ToggleButton>
                    <ToggleButton value="hotel">Hotel</ToggleButton>
                    <ToggleButton value="motel">Motel</ToggleButton>
                </ToggleButtonGroup>
            </Box>
            <AddBookingDialog
                open={addBookingDialogOpen}
                onClose={() => setAddBookingDialogOpen(false)}
                onAdd={onAdd}
            />
        </>
    );
};

export default BookingsPage;