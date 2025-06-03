package mk.ukim.finki.fooddeliverybackend.service.domain;

import mk.ukim.finki.fooddeliverybackend.model.domain.Dish;
import mk.ukim.finki.fooddeliverybackend.model.domain.Order;

import java.util.List;
import java.util.Optional;

public interface DishService {
    List<Dish> findAll();

    Optional<Dish> findById(Long id);

    Dish save(Dish menuItem);

    Optional<Dish> update(Long id, Dish menuItem);

    Optional<Dish> deleteById(Long id);

    Order addToOrder(Dish dish, Order order);

    Order removeFromOrder(Dish dish, Order order);
}
