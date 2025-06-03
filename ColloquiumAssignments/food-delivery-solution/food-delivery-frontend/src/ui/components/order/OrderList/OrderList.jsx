import React from 'react';
import {
    Box,
    Card,
    CardContent,
    Typography,
    IconButton,
    Button,
    Divider,
    List,
    ListItem,
    ListItemText, CircularProgress, Stack,
} from '@mui/material';
import {Delete} from '@mui/icons-material';
import useOrder from "../../../../hooks/useOrder.js";
import "./OrderList.css";
import dishRepository from "../../../../repository/dishRepository.js";

const OrderList = () => {
    const {order, fetchPendingOrder, confirmPendingOrder, cancelPendingOrder} = useOrder();

    if (!order)
        return <Box className="progress-box"><CircularProgress/></Box>;

    const {dishes} = order;

    const removeFromOrder = (id) => {
        dishRepository.removeFromOrder(id)
            .then(() => {
                fetchPendingOrder();
                console.log(`Successfully removed the dish with ID: ${id}!`)
            })
            .catch((error) => {
                console.log(error);
            });
    };

    const getTotal = () =>
        dishes.reduce((sum, product) => sum + product.price, 0).toFixed(2);

    return (
        <Box my={3} width={500} mx="auto">
            <Card>
                <CardContent>
                    <Stack direction="row" justifyContent="space-between" alignItems="center">
                        <Typography variant="h5" gutterBottom>
                            Order
                        </Typography>
                        <Typography variant="h6" gutterBottom>
                            Status: {order.status}
                        </Typography>
                    </Stack>
                    <Divider sx={{mb: 2}}/>
                    <List className="order-list">
                        {dishes.map(item => (
                            <ListItem
                                key={item.id}
                                secondaryAction={<IconButton edge="end" color="error"
                                                             className="remove-from-order"
                                                             onClick={() => removeFromOrder(item.id)}><Delete/></IconButton>}
                            >
                                <ListItemText
                                    className="order-item"
                                    primary={item.name}
                                    secondary={`$${item.price.toFixed(2)}`}
                                />
                            </ListItem>
                        ))}
                    </List>
                    <Divider sx={{my: 2}}/>
                    <Typography variant="h6">Total: ${getTotal()}</Typography>
                    <Stack direction="row" justifyContent="space-between" alignItems="center">
                        <Button
                            variant="outlined"
                            color="error"
                            fullWidth
                            sx={{mt: 2, mr: 2}}
                            className="cancel-btn"
                            onClick={cancelPendingOrder}>
                            CANCEL
                        </Button>
                        <Button
                            variant="contained"
                            color="primary"
                            fullWidth
                            sx={{mt: 2}}
                            className="confirm-btn"
                            onClick={confirmPendingOrder}>
                            CONFIRM
                        </Button>
                    </Stack>
                </CardContent>
            </Card>
        </Box>
    );
};

export default OrderList;
