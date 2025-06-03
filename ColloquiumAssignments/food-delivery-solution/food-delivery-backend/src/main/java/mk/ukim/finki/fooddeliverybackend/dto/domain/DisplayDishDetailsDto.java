package mk.ukim.finki.fooddeliverybackend.dto.domain;

import mk.ukim.finki.fooddeliverybackend.model.domain.Dish;

public record DisplayDishDetailsDto(
        Long id,
        String name,
        String description,
        Double price,
        Integer quantity,
        DisplayRestaurantDto restaurant
) {

    public static DisplayDishDetailsDto from(Dish dish) {
        return new DisplayDishDetailsDto(
                dish.getId(),
                dish.getName(),
                dish.getDescription(),
                dish.getPrice(),
                dish.getQuantity(),
                DisplayRestaurantDto.from(dish.getRestaurant())
        );
    }

}
