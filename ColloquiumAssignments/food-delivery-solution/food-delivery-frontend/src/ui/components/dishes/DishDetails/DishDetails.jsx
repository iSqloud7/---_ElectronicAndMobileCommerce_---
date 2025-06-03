import React from 'react';
import {useNavigate, useParams} from "react-router";
import {
    Box,
    Button,
    CircularProgress,
    Typography,
    Paper,
    Stack,
    Chip,
    Breadcrumbs,
    Link
} from "@mui/material";
import {
    ArrowBack,
    Restaurant,
    ShoppingCart
} from "@mui/icons-material";
import useDishDetails from "../../../../hooks/useDishDetails.js";
import dishRepository from "../../../../repository/dishRepository.js";

const DishDetails = () => {
    const navigate = useNavigate();
    const {id} = useParams();
    const dish = useDishDetails(id);

    if (!dish) {
        return (
            <Box sx={{display: 'flex', justifyContent: 'center', alignItems: 'center', minHeight: '60vh'}}>
                <CircularProgress/>
            </Box>
        );
    }

    const addToOrder = () => {
        dishRepository.addToOrder(id)
            .then(() => {
                console.log(`Successfully ordered the dish with ID: ${id}!`);
            })
            .catch((error) => {
                console.log(error);
            });
    };

    return (
        <Box width={750} mx="auto" mt={3}>
            <Breadcrumbs aria-label="breadcrumb" sx={{mb: 3}}>
                <Link
                    underline="hover"
                    color="inherit"
                    href="#"
                    onClick={(e) => {
                        e.preventDefault();
                        navigate("/dishes");
                    }}
                >
                    Dishes
                </Link>
                <Typography color="text.primary">{dish.name}</Typography>
            </Breadcrumbs>

            <Paper elevation={2} sx={{p: 4, borderRadius: 4}}>
                <Stack spacing={3}>
                    <Typography variant="h4" fontWeight={600} className="dish-name">
                        {dish.name}
                    </Typography>

                    <Typography variant="body1" color="text.secondary" className="dish-desc">
                        {dish.description || "No description provided."}
                    </Typography>

                    <Typography variant="h5" color="primary.main" className="dish-price">
                        ${dish.price}
                    </Typography>

                    <Typography variant="subtitle1" className="dish-quantity">
                        {dish.quantity} serving(s) available
                    </Typography>

                    <Chip
                        icon={<Restaurant/>}
                        label={dish.restaurant.name}
                        className="restaurant-name"
                        color="secondary"
                        variant="outlined"
                        sx={{width: "fit-content", p: 2}}
                    />

                    <Stack direction="row" justifyContent="space-between" spacing={2} mt={2}>
                        <Button
                            variant="contained"
                            color="primary"
                            startIcon={<ShoppingCart/>}
                            className="add-to-order"
                            onClick={addToOrder}
                        >
                            Order Now
                        </Button>
                        <Button
                            variant="outlined"
                            startIcon={<ArrowBack/>}
                            onClick={() => navigate("/dishes")}
                        >
                            Back to Dishes
                        </Button>
                    </Stack>
                </Stack>
            </Paper>
        </Box>
    );
};

export default DishDetails;
