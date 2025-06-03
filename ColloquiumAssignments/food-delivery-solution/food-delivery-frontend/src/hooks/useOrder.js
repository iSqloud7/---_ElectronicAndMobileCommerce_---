import {useCallback, useEffect, useState} from "react";
import orderRepository from "../repository/orderRepository.js";

const useOrder = () => {
    const [order, setOrder] = useState(null);

    const fetchPendingOrder = useCallback(() => {
        orderRepository.findPending()
            .then((response) => {
                setOrder(response.data);
            })
            .catch((error) => {
                console.log(error);
            });
    }, []);

    const confirmPendingOrder = useCallback(() => {
        orderRepository.confirmPendingOrder()
            .then(() => {
                fetchPendingOrder();
            })
            .catch((error) => {
                console.log(error);
            });
    }, [fetchPendingOrder]);

    const cancelPendingOrder = useCallback(() => {
        orderRepository.cancelPendingOrder()
            .then(() => {
                fetchPendingOrder();
            })
            .catch((error) => {
                console.log(error);
            });
    }, [fetchPendingOrder]);

    useEffect(() => {
        fetchPendingOrder();
    }, [fetchPendingOrder]);

    return {order, fetchPendingOrder, confirmPendingOrder, cancelPendingOrder};
};

export default useOrder;