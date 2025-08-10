package mk.ukim.finki.eimt.lab.Service.Domain;

import mk.ukim.finki.eimt.lab.Model.DTO.WishlistDTO.DisplayWishlistDTO;

import java.util.Optional;

public interface WishlistDomainService {

    Optional<DisplayWishlistDTO> addBookToWishlist(Long bookID, String username);

    Optional<DisplayWishlistDTO> clearWishlist(String username);
}
