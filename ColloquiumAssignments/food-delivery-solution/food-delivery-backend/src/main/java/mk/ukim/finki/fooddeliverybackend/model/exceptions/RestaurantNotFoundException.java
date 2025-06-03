package mk.ukim.finki.fooddeliverybackend.model.exceptions;

public class RestaurantNotFoundException extends RuntimeException {

    public RestaurantNotFoundException(Long id) {
        super(String.format("Restaurant with ID %s does not exist.", id));
    }

}
