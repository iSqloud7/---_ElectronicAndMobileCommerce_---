package mk.ukim.finki.fooddeliverybackend.service.application.impl;

import mk.ukim.finki.fooddeliverybackend.dto.domain.DisplayOrderDto;
import mk.ukim.finki.fooddeliverybackend.service.application.OrderApplicationService;
import mk.ukim.finki.fooddeliverybackend.service.domain.OrderService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderApplicationServiceImpl implements OrderApplicationService {

    private final OrderService orderService;

    public OrderApplicationServiceImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public DisplayOrderDto findOrCreatePending(String username) {
        return DisplayOrderDto.from(this.orderService.findOrCreatePending(username));
    }

    @Override
    public Optional<DisplayOrderDto> confirm(String username) {
        return this.orderService.confirm(username)
                .map(DisplayOrderDto::from);
    }

    @Override
    public Optional<DisplayOrderDto> cancel(String username) {
        return this.orderService.cancel(username)
                .map(DisplayOrderDto::from);
    }
}
