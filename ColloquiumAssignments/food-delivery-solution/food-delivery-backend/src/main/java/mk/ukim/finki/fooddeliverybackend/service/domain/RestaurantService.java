package mk.ukim.finki.fooddeliverybackend.service.domain;

import mk.ukim.finki.fooddeliverybackend.model.domain.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {
    List<Restaurant> findAll();

    Optional<Restaurant> findById(Long id);

    Restaurant save(Restaurant restaurant);

    Optional<Restaurant> update(Long id, Restaurant restaurant);

    Optional<Restaurant> deleteById(Long id);
}
