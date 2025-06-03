package mk.ukim.finki.fooddeliverybackend.service.domain;


import mk.ukim.finki.fooddeliverybackend.model.domain.Order;

import java.util.Optional;

public interface OrderService {
    Optional<Order> findPending(String username);

    Order findOrCreatePending(String username);

    Optional<Order> confirm(String username);

    Optional<Order> cancel(String username);
}
