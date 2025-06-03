package mk.ukim.finki.fooddeliverybackend.dto.domain;

import mk.ukim.finki.fooddeliverybackend.model.domain.Order;
import mk.ukim.finki.fooddeliverybackend.model.enums.OrderStatus;

import java.util.List;

public record DisplayOrderDto(
        String username,
        List<DisplayDishDto> dishes,
        OrderStatus status
) {

    public static DisplayOrderDto from(Order order) {
        return new DisplayOrderDto(
                order.getUser().getUsername(),
                DisplayDishDto.from(order.getDishes()),
                order.getStatus()
        );
    }

}
