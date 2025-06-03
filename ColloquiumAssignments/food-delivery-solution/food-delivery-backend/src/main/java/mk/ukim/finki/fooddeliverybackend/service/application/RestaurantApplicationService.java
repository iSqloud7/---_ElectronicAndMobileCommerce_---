package mk.ukim.finki.fooddeliverybackend.service.application;

import mk.ukim.finki.fooddeliverybackend.dto.domain.CreateRestaurantDto;
import mk.ukim.finki.fooddeliverybackend.dto.domain.DisplayRestaurantDto;

import java.util.List;
import java.util.Optional;

public interface RestaurantApplicationService {
    List<DisplayRestaurantDto> findAll();

    Optional<DisplayRestaurantDto> findById(Long id);

    DisplayRestaurantDto save(CreateRestaurantDto createRestaurantDto);

    Optional<DisplayRestaurantDto> update(Long id, CreateRestaurantDto createRestaurantDto);

    Optional<DisplayRestaurantDto> deleteById(Long id);
}
