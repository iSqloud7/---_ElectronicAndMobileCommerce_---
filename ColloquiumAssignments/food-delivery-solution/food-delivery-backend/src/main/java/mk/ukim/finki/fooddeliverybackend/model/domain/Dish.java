package mk.ukim.finki.fooddeliverybackend.model.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double price;

    private Integer quantity;

    @ManyToOne
    private Restaurant restaurant;

    public Dish() {
    }

    public Dish(String name, String description, Double price, Integer quantity, Restaurant restaurant) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.restaurant = restaurant;
    }

    public void increaseQuantity() {
        quantity++;
    }

    public void decreaseQuantity() {
        quantity--;
    }

}
