package mk.ukim.finki.eimt.aud.Service.Application.Implementation;

import mk.ukim.finki.eimt.aud.Model.DTO.DisplayProductDTO;
import mk.ukim.finki.eimt.aud.Model.DTO.ShoppingCartDTO;
import mk.ukim.finki.eimt.aud.Service.Application.ShoppingCartApplicationService;
import mk.ukim.finki.eimt.aud.Service.Domain.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartApplicationServiceImpl implements ShoppingCartApplicationService {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartApplicationServiceImpl(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public List<DisplayProductDTO> listAllProductsInShoppingCart(Long shoppingCartID) {
        return DisplayProductDTO.from(this.shoppingCartService.listAllProductsInShoppingCart(shoppingCartID));
    }

    @Override
    public Optional<ShoppingCartDTO> getActiveShoppingCart(String username) {
        return this.shoppingCartService.getActiveShoppingCart(username)
                .map(ShoppingCartDTO::from);
    }

    @Override
    public Optional<ShoppingCartDTO> addProductToShoppingCart(String username, Long productID) {
        return this.shoppingCartService.addProductToShoppingCart(username, productID)
                .map(ShoppingCartDTO::from);
    }
}