package mk.ukim.finki.fooddeliverybackend.service.application;

import mk.ukim.finki.fooddeliverybackend.dto.domain.DisplayOrderDto;

import java.util.Optional;

public interface OrderApplicationService {
    DisplayOrderDto findOrCreatePending(String username);

    Optional<DisplayOrderDto> confirm(String username);

    Optional<DisplayOrderDto> cancel(String username);
}
