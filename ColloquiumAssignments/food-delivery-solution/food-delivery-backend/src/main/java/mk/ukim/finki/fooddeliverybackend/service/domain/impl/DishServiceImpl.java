package mk.ukim.finki.fooddeliverybackend.service.domain.impl;

import mk.ukim.finki.fooddeliverybackend.dto.domain.DisplayOrderDto;
import mk.ukim.finki.fooddeliverybackend.model.domain.Dish;
import mk.ukim.finki.fooddeliverybackend.model.domain.Order;
import mk.ukim.finki.fooddeliverybackend.model.exceptions.DishOutOfStockException;
import mk.ukim.finki.fooddeliverybackend.repository.DishRepository;
import mk.ukim.finki.fooddeliverybackend.repository.OrderRepository;
import mk.ukim.finki.fooddeliverybackend.service.domain.DishService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;
    private final OrderRepository orderRepository;

    public DishServiceImpl(DishRepository dishRepository, OrderRepository orderRepository) {
        this.dishRepository = dishRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Dish> findAll() {
        return this.dishRepository.findAll();
    }

    @Override
    public Optional<Dish> findById(Long id) {
        return dishRepository.findById(id);
    }

    @Override
    public Dish save(Dish dish) {
        return this.dishRepository.save(dish);
    }

    @Override
    public Optional<Dish> update(Long id, Dish dish) {
        return findById(id)
                .map((existing_dish) -> {
                    existing_dish.setName(dish.getName());
                    existing_dish.setDescription(dish.getDescription());
                    existing_dish.setPrice(dish.getPrice());
                    existing_dish.setQuantity(dish.getQuantity());
                    existing_dish.setRestaurant(dish.getRestaurant());

                    return this.dishRepository.save(existing_dish);
                });
    }

    @Override
    public Optional<Dish> deleteById(Long id) {
        Optional<Dish> dish_obj = findById(id);

        if (dish_obj.isPresent()) {
            Dish dish = dish_obj.get();

            this.dishRepository.delete(dish);
        }

        return dish_obj;
    }

    @Override
    public Order addToOrder(Dish dish, Order order) {
        if (dish.getQuantity() == 0) {
            throw new DishOutOfStockException(dish.getId());
        }

        order.getDishes().add(dish);
        dish.setQuantity(dish.getQuantity() - 1);

        return this.orderRepository.save(order);
    }

    @Override
    public Order removeFromOrder(Dish dish, Order order) {
        order.getDishes().remove(dish);
        dish.setQuantity(dish.getQuantity() + 1);

        return this.orderRepository.save(order);
    }
}
