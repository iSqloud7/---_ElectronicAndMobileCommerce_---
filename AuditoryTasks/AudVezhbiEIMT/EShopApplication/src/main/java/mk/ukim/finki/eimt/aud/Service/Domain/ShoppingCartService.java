package mk.ukim.finki.eimt.aud.Service.Domain;

import mk.ukim.finki.eimt.aud.Model.Entity.Product;
import mk.ukim.finki.eimt.aud.Model.Entity.ShoppingCart;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {

    List<Product> listAllProductsInShoppingCart(Long shoppingCartID);

    Optional<ShoppingCart> getActiveShoppingCart(String username);

    Optional<ShoppingCart> addProductToShoppingCart(String username, Long productID);
}