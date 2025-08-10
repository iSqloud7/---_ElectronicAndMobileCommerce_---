import React, {useState} from 'react';
import {useNavigate} from "react-router-dom";
import {Box, Button, Card, CardActions, CardContent, Typography} from "@mui/material";
import EditBookingDialog from "../EditBookingDialog/EditBookingDialog.jsx";
import DeleteBookingDialog from "../DeleteBookingDialog/DeleteBookingDialog.jsx";
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';

// Card component for displaying a booking!

const BookingCard = ({booking, onEdit, onDelete}) => {

    // Hook for navigation!
    const navigate = useNavigate()

    // States for controlling dialog visibility!
    const [editBookingDialogOpen, setEditBookingDialogOpen] = useState(false);
    const [deleteBookingDialogOpen, setDeleteBookingDialogOpen] = useState(false)

    return (
        <>
            <Card sx={{boxShadow: 3, borderRadius: 2, p: 1}}>
                <CardContent>
                    <Typography variant="h5">{booking.name}</Typography>
                    <Typography variant="subtitle2">
                        Host ID: {booking.hostId}
                    </Typography>
                    <Typography variant="body1" fontWeight="bold"
                                sx={{
                                    textAlign: "right",
                                    fontSize: "1.25rem"
                                }}>{booking.booked ? "Booked" : "Not Booked"}</Typography>
                    <Typography variant="body2"
                                sx={{textAlign: "right"}}>Rooms:{booking.numRooms} available</Typography>
                </CardContent>
                <CardActions sx={{justifyContent: "space-between"}}>
                    <Box>
                        <Button
                            size="small"
                            color="warning"
                            startIcon={<EditIcon/>}
                            sx={{mr: "0.25rem"}}
                            onClick={() => setEditBookingDialogOpen(true)}
                        >
                            Edit
                        </Button>
                        <Button
                            size="small"
                            color="error"
                            startIcon={<DeleteIcon/>}
                            onClick={() => setDeleteBookingDialogOpen(true)}
                        >
                            Delete
                        </Button>
                    </Box>
                </CardActions>
            </Card>
            <EditBookingDialog
                open={editBookingDialogOpen}
                onClose={() => setEditBookingDialogOpen(false)}
                accommodation={booking}
                onEdit={onEdit}
            />
            <DeleteBookingDialog
                open={deleteBookingDialogOpen}
                onClose={() => setDeleteBookingDialogOpen(false)}
                booking={booking}
                onDelete={onDelete}
            />
        </>
    );
};

export default BookingCard;