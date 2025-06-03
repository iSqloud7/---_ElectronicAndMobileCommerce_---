package mk.ukim.finki.fooddeliverybackend.service.domain.impl;

import mk.ukim.finki.fooddeliverybackend.model.domain.Restaurant;
import mk.ukim.finki.fooddeliverybackend.repository.RestaurantRepository;
import mk.ukim.finki.fooddeliverybackend.service.domain.RestaurantService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Optional<Restaurant> findById(Long id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Optional<Restaurant> update(Long id, Restaurant restaurant) {
        return findById(id)
                .map(existingRestaurant -> {
                    existingRestaurant.setName(restaurant.getName());
                    existingRestaurant.setDescription(restaurant.getDescription());
                    return restaurantRepository.save(existingRestaurant);
                });
    }

    @Override
    public Optional<Restaurant> deleteById(Long id) {
        Optional<Restaurant> restaurant = findById(id);
        restaurant.ifPresent(restaurantRepository::delete);
        return restaurant;
    }

}
