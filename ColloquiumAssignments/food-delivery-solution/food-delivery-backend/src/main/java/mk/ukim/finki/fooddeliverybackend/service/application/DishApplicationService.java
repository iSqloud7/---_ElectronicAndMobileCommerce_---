package mk.ukim.finki.fooddeliverybackend.service.application;


import mk.ukim.finki.fooddeliverybackend.dto.domain.CreateDishDto;
import mk.ukim.finki.fooddeliverybackend.dto.domain.DisplayDishDetailsDto;
import mk.ukim.finki.fooddeliverybackend.dto.domain.DisplayDishDto;
import mk.ukim.finki.fooddeliverybackend.dto.domain.DisplayOrderDto;
import mk.ukim.finki.fooddeliverybackend.model.domain.Dish;
import mk.ukim.finki.fooddeliverybackend.model.domain.Order;

import java.util.List;
import java.util.Optional;

public interface DishApplicationService {
    List<DisplayDishDto> findAll();

    Optional<DisplayDishDto> findById(Long id);

    Optional<DisplayDishDetailsDto> findByIdWithDetails(Long id);

    DisplayDishDto save(CreateDishDto createMenuItemDto);

    Optional<DisplayDishDto> update(Long id, CreateDishDto createMenuItemDto);

    Optional<DisplayDishDto> deleteById(Long id);

    DisplayOrderDto addToOrder(Long id, String username);

    DisplayOrderDto removeFromOrder(Long id, String username);
}
