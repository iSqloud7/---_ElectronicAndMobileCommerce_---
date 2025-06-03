package mk.ukim.finki.fooddeliverybackend.dto.domain;

import mk.ukim.finki.fooddeliverybackend.model.domain.Dish;

import java.util.List;

public record DisplayDishDto(
        Long id,
        String name,
        String description,
        Double price,
        Integer quantity,
        Long restaurantId
) {

    public static DisplayDishDto from(Dish dish) {
        return new DisplayDishDto(
                dish.getId(),
                dish.getName(),
                dish.getDescription(),
                dish.getPrice(),
                dish.getQuantity(),
                dish.getRestaurant().getId()
        );
    }

    public static List<DisplayDishDto> from(List<Dish> dishes) {
        return dishes
                .stream()
                .map(DisplayDishDto::from)
                .toList();
    }

}
