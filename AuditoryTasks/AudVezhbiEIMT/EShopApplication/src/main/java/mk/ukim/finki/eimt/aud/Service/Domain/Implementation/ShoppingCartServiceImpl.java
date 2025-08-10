package mk.ukim.finki.eimt.aud.Service.Domain.Implementation;

import mk.ukim.finki.eimt.aud.Model.Entity.Product;
import mk.ukim.finki.eimt.aud.Model.Entity.ShoppingCart;
import mk.ukim.finki.eimt.aud.Model.Entity.User;
import mk.ukim.finki.eimt.aud.Model.Enumerations.ShoppingCartStatus;
import mk.ukim.finki.eimt.aud.Model.Exceptions.ProductAlreadyInShoppingCartException;
import mk.ukim.finki.eimt.aud.Model.Exceptions.ProductNotFoundException;
import mk.ukim.finki.eimt.aud.Model.Exceptions.ShoppingCartNotFoundException;
import mk.ukim.finki.eimt.aud.Repository.ShoppingCartRepository;
import mk.ukim.finki.eimt.aud.Service.Domain.ProductService;
import mk.ukim.finki.eimt.aud.Service.Domain.ShoppingCartService;
import mk.ukim.finki.eimt.aud.Service.Domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserService userService;
    private final ProductService productService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, UserService userService, ProductService productService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public List<Product> listAllProductsInShoppingCart(Long shoppingCartID) {

        if (this.shoppingCartRepository.findById(shoppingCartID).isEmpty()) {
            throw new ShoppingCartNotFoundException(shoppingCartID);
        }

        return this.shoppingCartRepository.findById(shoppingCartID).get().getProducts();
    }

    @Override
    public Optional<ShoppingCart> getActiveShoppingCart(String username) {
        User user_obj = this.userService.findByUsername(username);

        return Optional.of(this.shoppingCartRepository.findByUserAndStatus(
                user_obj,
                ShoppingCartStatus.CREATED).orElseGet(() -> this.shoppingCartRepository.save(new ShoppingCart(user_obj))));
    }

    @Override
    public Optional<ShoppingCart> addProductToShoppingCart(String username, Long productID) {

        if (getActiveShoppingCart(username).isPresent()) {
            ShoppingCart shoppingCart_obj = getActiveShoppingCart(username).get();
            Product product_obj = this.productService.findByID(productID).orElseThrow(() -> new ProductNotFoundException(productID));

            if (!shoppingCart_obj.getProducts().stream()
                    .filter(product -> product.getID().equals(productID))
                    .collect(Collectors.toList())
                    .isEmpty()) {
                throw new ProductAlreadyInShoppingCartException(productID, username);
            }

            shoppingCart_obj.getProducts().add(product_obj);

            return Optional.of(this.shoppingCartRepository.save(shoppingCart_obj));
        }

        return Optional.empty();
    }
}