package mk.ukim.finki.fooddeliverybackend.dto.domain;

import mk.ukim.finki.fooddeliverybackend.model.domain.Restaurant;

import java.util.List;

public record DisplayRestaurantDto(
        Long id,
        String name,
        String description
) {

    public static DisplayRestaurantDto from(Restaurant restaurant) {
        return new DisplayRestaurantDto(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getDescription()
        );
    }

    public static List<DisplayRestaurantDto> from(List<Restaurant> restaurants) {
        return restaurants
                .stream()
                .map(DisplayRestaurantDto::from)
                .toList();
    }

}
