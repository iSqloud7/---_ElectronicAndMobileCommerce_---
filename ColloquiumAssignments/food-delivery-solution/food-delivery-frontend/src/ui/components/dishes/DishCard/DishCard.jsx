import React, {useState} from 'react';
import InfoIcon from '@mui/icons-material/Info';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import {Card, CardContent, Typography, CardActions, Button, Box} from '@mui/material';
import EditDishDialog from "../EditDishDialog/EditDishDialog.jsx";
import DeleteDishDialog from "../DeleteDishDialog/DeleteDishDialog.jsx";
import {useNavigate} from "react-router";

const DishCard = ({dish, onEdit, onDelete}) => {
    const navigate = useNavigate();

    const [editDishDialogOpen, setEditDishDialogOpen] = useState(false);
    const [deleteDishDialogOpen, setDeleteDishDialogOpen] = useState(false);

    return (
        <>
            <Card
                sx={{
                    borderRadius: 2,
                    p: 1,
                    flexGrow: 1,
                    display: "flex",
                    flexDirection: "column",
                    justifyContent: "space-between"
                }}
                className="card"
                data-id={dish.id}
            >
                <CardContent sx={{pb: 0}}>
                    <Typography gutterBottom variant="h5" component="div" className="dish-name">
                        {dish.name}
                    </Typography>
                    <Typography variant="body2" color="text.secondary" sx={{mb: 1.5}}  className="dish-desc">
                        {dish.description}
                    </Typography>
                    <Box display="flex" justifyContent="space-between" alignItems="center">
                        <Typography variant="body1" sx={{fontWeight: 'bold'}}>
                            ${dish.price.toFixed(2)}
                        </Typography>
                        <Typography variant="body2" color={dish.quantity > 0 ? 'success.main' : 'error.main'}>
                            {dish.quantity > 0 ? `In Stock: ${dish.quantity}` : 'Out of stock'}
                        </Typography>
                    </Box>
                </CardContent>
                <CardActions sx={{justifyContent: "space-between"}}>
                    <Button
                        size="small"
                        color="info"
                        startIcon={<InfoIcon/>}
                        onClick={() => navigate(`/dishes/${dish.id}`)}
                        className="info-item"
                    >
                        Info
                    </Button>
                    <Box>
                        <Button
                            size="small"
                            color="warning"
                            startIcon={<EditIcon/>}
                            sx={{mr: "0.25rem"}}
                            onClick={() => setEditDishDialogOpen(true)}
                            className="edit-item"
                        >
                            Edit
                        </Button>
                        <Button
                            size="small"
                            color="error"
                            startIcon={<DeleteIcon/>}
                            onClick={() => setDeleteDishDialogOpen(true)}
                            className="delete-item"
                        >
                            Delete
                        </Button>
                    </Box>
                </CardActions>
            </Card>
            <EditDishDialog
                open={editDishDialogOpen}
                onClose={() => setEditDishDialogOpen(false)}
                onEdit={onEdit}
                dish={dish}
            />
            <DeleteDishDialog
                open={deleteDishDialogOpen}
                onClose={() => setDeleteDishDialogOpen(false)}
                onDelete={onDelete}
                dish={dish}
            />
        </>
    );
};

export default DishCard;