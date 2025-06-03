package mk.ukim.finki.fooddeliverybackend.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.fooddeliverybackend.model.enums.OrderStatus;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "customer_orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Dish> dishes;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order() {
    }

    public Order(User user) {
        this.user = user;
        dishes = new ArrayList<>();
        status = OrderStatus.PENDING;
    }

    public void confirm() {
        status = OrderStatus.CONFIRMED;
    }

    public void cancel() {
        status = OrderStatus.CANCELED;
    }

}
