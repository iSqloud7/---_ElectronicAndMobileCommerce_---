package mk.ukim.finki.eimt.lab.Service.Application.Implementation;

import mk.ukim.finki.eimt.lab.Model.DTO.WishlistDTO.DisplayWishlistDTO;
import mk.ukim.finki.eimt.lab.Service.Application.WishlistApplicationService;
import mk.ukim.finki.eimt.lab.Service.Domain.WishlistDomainService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WishlistApplicationServiceImpl implements WishlistApplicationService {

    private final WishlistDomainService wishlistDomainService;

    public WishlistApplicationServiceImpl(WishlistDomainService wishlistDomainService) {
        this.wishlistDomainService = wishlistDomainService;
    }

    @Override
    public Optional<DisplayWishlistDTO> addBookToWishlist(Long bookID, String username) {
        return this.wishlistDomainService.addBookToWishlist(bookID, username);
    }

    @Override
    public Optional<DisplayWishlistDTO> clearWishlist(String username) {
        return this.wishlistDomainService.clearWishlist(username);
    }
}
