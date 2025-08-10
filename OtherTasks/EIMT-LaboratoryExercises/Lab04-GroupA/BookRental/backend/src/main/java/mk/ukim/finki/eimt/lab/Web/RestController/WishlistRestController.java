package mk.ukim.finki.eimt.lab.Web.RestController;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.eimt.lab.Model.DTO.WishlistDTO.AddBookToWishlistDTO;
import mk.ukim.finki.eimt.lab.Model.DTO.UserDTO.DisplayUserDTO;
import mk.ukim.finki.eimt.lab.Model.DTO.WishlistDTO.DisplayWishlistDTO;
import mk.ukim.finki.eimt.lab.Service.Application.WishlistApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistRestController {

    private final WishlistApplicationService wishlistApplicationService;

    public WishlistRestController(WishlistApplicationService wishlistApplicationService) {
        this.wishlistApplicationService = wishlistApplicationService;
    }

    @PostMapping("/add-book")
    public ResponseEntity<DisplayWishlistDTO> addBookToWishlist(@RequestBody AddBookToWishlistDTO addBookToWishlistDTO,
                                                                HttpServletRequest request) throws Exception {
        DisplayUserDTO user_obj = (DisplayUserDTO) request.getSession().getAttribute("user");

        return this.wishlistApplicationService.addBookToWishlist(addBookToWishlistDTO.bookID(), user_obj.username())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/clear-book")
    public ResponseEntity<DisplayWishlistDTO> clearWishlist(HttpServletRequest request) throws Exception {
        DisplayUserDTO user_obj = (DisplayUserDTO) request.getSession().getAttribute("user");

        return this.wishlistApplicationService.clearWishlist(user_obj.username())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
