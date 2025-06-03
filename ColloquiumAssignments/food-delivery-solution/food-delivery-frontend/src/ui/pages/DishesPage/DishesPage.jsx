import React, {useState} from 'react';
import {Box, Button, CircularProgress} from "@mui/material";
import "./DishesPage.css";
import useDishes from "../../../hooks/useDishes.js";
import DishesGrid from "../../components/dishes/DishesGrid/DishesGrid.jsx";
import AddDishDialog from "../../components/dishes/AddDishDialog/AddDishDialog.jsx";

const ProductsPage = () => {
    const {dishes, loading, onAdd, onEdit, onDelete} = useDishes();

    const [addDishDialogOpen, setAddDishDialogOpen] = useState(false);

    return (
        <>
            <Box className="dishes-box">
                <Box sx={{display: "flex", justifyContent: "flex-end", mb: 2}}>
                    <Button
                        variant="contained"
                        color="primary"
                        onClick={() => setAddDishDialogOpen(true)}
                        className="add-item"
                    >
                        Add Dish
                    </Button>
                </Box>
                {loading && <Box className="progress-box"><CircularProgress/></Box>}
                {!loading && <DishesGrid dishes={dishes} onEdit={onEdit} onDelete={onDelete}/>}
            </Box>
            <AddDishDialog
                open={addDishDialogOpen}
                onClose={() => setAddDishDialogOpen(false)}
                onAdd={onAdd}
            />
        </>
    );
};

export default ProductsPage;