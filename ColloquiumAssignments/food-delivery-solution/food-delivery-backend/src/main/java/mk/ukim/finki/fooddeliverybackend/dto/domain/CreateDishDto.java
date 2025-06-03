package mk.ukim.finki.fooddeliverybackend.dto.domain;

import mk.ukim.finki.fooddeliverybackend.model.domain.Dish;
import mk.ukim.finki.fooddeliverybackend.model.domain.Restaurant;

public record CreateDishDto(
        String name,
        String description,
        Double price,
        Integer quantity,
        Long restaurantId
) {

    public Dish toDish(Restaurant restaurant) {
        return new Dish(
                name,
                description,
                price,
                quantity,
                restaurant
        );
    }

}
