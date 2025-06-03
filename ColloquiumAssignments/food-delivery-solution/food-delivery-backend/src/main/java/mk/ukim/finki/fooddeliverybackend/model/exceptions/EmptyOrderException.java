package mk.ukim.finki.fooddeliverybackend.model.exceptions;

public class EmptyOrderException extends RuntimeException {

    public EmptyOrderException() {
        super("The order is empty.");
    }

}
