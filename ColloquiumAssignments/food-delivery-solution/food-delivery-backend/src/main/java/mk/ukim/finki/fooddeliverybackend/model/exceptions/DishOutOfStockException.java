package mk.ukim.finki.fooddeliverybackend.model.exceptions;

public class DishOutOfStockException extends RuntimeException {

    public DishOutOfStockException(Long id) {
        super(String.format("Dish with ID %s is out of stock.", id));
    }

}
