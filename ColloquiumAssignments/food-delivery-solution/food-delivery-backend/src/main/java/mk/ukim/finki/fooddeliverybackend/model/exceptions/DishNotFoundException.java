package mk.ukim.finki.fooddeliverybackend.model.exceptions;

public class DishNotFoundException extends RuntimeException {

    public DishNotFoundException(Long id) {
        super(String.format("Dish with ID %s does not exist.", id));
    }

}
