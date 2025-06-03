import {useEffect, useState} from "react";
import dishRepository from "../repository/dishRepository.js";

const useDishDetails = (id) => {
    const [dish, setDish] = useState(null);

    useEffect(() => {
        dishRepository.findByIdWithDetails(id)
            .then((response) => {
                setDish(response.data);
            })
            .catch((error) => {
                console.log(error)
            });
    }, [id]);

    return dish;
};

export default useDishDetails;