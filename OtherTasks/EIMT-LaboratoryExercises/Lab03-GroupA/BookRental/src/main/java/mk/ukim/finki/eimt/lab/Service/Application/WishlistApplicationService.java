package mk.ukim.finki.eimt.lab.Service.Application;

import mk.ukim.finki.eimt.lab.Model.DTO.WishlistDTO.DisplayWishlistDTO;

import java.util.Optional;

public interface WishlistApplicationService {

    Optional<DisplayWishlistDTO> addBookToWishlist(Long ID, String username);

    Optional<DisplayWishlistDTO> clearWishlist(String username);
}
