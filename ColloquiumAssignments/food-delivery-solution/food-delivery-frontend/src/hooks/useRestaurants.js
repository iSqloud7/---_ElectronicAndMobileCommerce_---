import {useEffect, useState} from "react";
import restaurantRepository from "../repository/restaurantRepository.js";

const useRestaurants = () => {
    const [restaurants, setRestaurants] = useState([]);

    useEffect(() => {
        restaurantRepository
            .findAll()
            .then((response) => setRestaurants(response.data))
            .catch((error) => console.log(error));
    }, []);

    return restaurants;
};

export default useRestaurants;