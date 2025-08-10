package mk.ukim.finki.eimt.aud.Model.DTO;

import mk.ukim.finki.eimt.aud.Model.Entity.ShoppingCart;
import mk.ukim.finki.eimt.aud.Model.Enumerations.ShoppingCartStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ShoppingCartDTO(

        Long ID,
        LocalDateTime dateCreated,
        DisplayUserDTO user,
        List<DisplayProductDTO> products,
        ShoppingCartStatus status
) {
    public static ShoppingCartDTO from(ShoppingCart shoppingCart) {
        return new ShoppingCartDTO(
                shoppingCart.getID(),
                shoppingCart.getDateCreated(),
                DisplayUserDTO.from(shoppingCart.getUser()),
                DisplayProductDTO.from(shoppingCart.getProducts()),
                shoppingCart.getStatus()
        );
    }
}