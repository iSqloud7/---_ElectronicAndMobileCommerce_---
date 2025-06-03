package mk.ukim.finki.fooddeliverybackend.web.controllers;

import mk.ukim.finki.fooddeliverybackend.dto.domain.CreateRestaurantDto;
import mk.ukim.finki.fooddeliverybackend.dto.domain.DisplayRestaurantDto;
import mk.ukim.finki.fooddeliverybackend.service.application.RestaurantApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantApplicationService restaurantApplicationService;

    public RestaurantController(RestaurantApplicationService restaurantApplicationService) {
        this.restaurantApplicationService = restaurantApplicationService;
    }

    @GetMapping
    public ResponseEntity<List<DisplayRestaurantDto>> findAll() {
        return ResponseEntity.ok(restaurantApplicationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayRestaurantDto> findById(@PathVariable Long id) {
        return restaurantApplicationService
                .findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<DisplayRestaurantDto> save(@RequestBody CreateRestaurantDto createRestaurantDto) {
        return ResponseEntity.ok(restaurantApplicationService.save(createRestaurantDto));
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<DisplayRestaurantDto> update(
            @PathVariable Long id,
            @RequestBody CreateRestaurantDto createRestaurantDto
    ) {
        return restaurantApplicationService
                .update(id, createRestaurantDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<DisplayRestaurantDto> deleteById(@PathVariable Long id) {
        return restaurantApplicationService
                .deleteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
