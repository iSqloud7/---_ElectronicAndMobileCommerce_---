package mk.ukim.finki.eimt.aud.Service.Application;

import mk.ukim.finki.eimt.aud.Model.DTO.DisplayProductDTO;
import mk.ukim.finki.eimt.aud.Model.DTO.ShoppingCartDTO;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartApplicationService {

    List<DisplayProductDTO> listAllProductsInShoppingCart(Long shoppingCartID);

    Optional<ShoppingCartDTO> getActiveShoppingCart(String username);

    Optional<ShoppingCartDTO> addProductToShoppingCart(String username, Long productID);
}