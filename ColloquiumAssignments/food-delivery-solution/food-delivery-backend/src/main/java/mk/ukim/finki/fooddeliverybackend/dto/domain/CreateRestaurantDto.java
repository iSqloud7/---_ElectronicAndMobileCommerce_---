package mk.ukim.finki.fooddeliverybackend.dto.domain;

import mk.ukim.finki.fooddeliverybackend.model.domain.Restaurant;

public record CreateRestaurantDto(
        String name,
        String description
) {

    public Restaurant toRestaurant() {
        return new Restaurant(name, description);
    }

}
