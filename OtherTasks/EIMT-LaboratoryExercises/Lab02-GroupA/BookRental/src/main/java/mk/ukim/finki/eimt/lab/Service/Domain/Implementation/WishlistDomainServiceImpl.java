package mk.ukim.finki.eimt.lab.Service.Domain.Implementation;

import mk.ukim.finki.eimt.lab.Model.Book;
import mk.ukim.finki.eimt.lab.Model.DTO.WishlistDTO.DisplayWishlistDTO;
import mk.ukim.finki.eimt.lab.Model.User;
import mk.ukim.finki.eimt.lab.Model.Wishlist;
import mk.ukim.finki.eimt.lab.Repository.BookRepository;
import mk.ukim.finki.eimt.lab.Repository.UserRepository;
import mk.ukim.finki.eimt.lab.Repository.WishlistRepository;
import mk.ukim.finki.eimt.lab.Service.Domain.WishlistDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistDomainServiceImpl implements WishlistDomainService {

    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public WishlistDomainServiceImpl(WishlistRepository wishlistRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.wishlistRepository = wishlistRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }


    @Override
    public Optional<DisplayWishlistDTO> addBookToWishlist(Long bookID, String username) {
        Optional<User> user_obj = this.userRepository.findByUsername(username);
        Optional<Book> book_obj = this.bookRepository.findById(bookID);

        if (book_obj.isPresent()) {
            Book existing_book = book_obj.get();
            User existing_user = user_obj.get();

            if (existing_book.getAvailableCopies() > 0) {
                existing_user.getWishlist().getBooks().add(existing_book);

                this.wishlistRepository.save(existing_user.getWishlist());

                return Optional.of(DisplayWishlistDTO.fromWishlistToDisplayWishlistDTO(existing_user.getWishlist().getBooks()));
            }
        }

        return Optional.empty();
    }

    @Override
    public Optional<DisplayWishlistDTO> clearWishlist(String username) {
        Optional<User> user_obj = this.userRepository.findByUsername(username);

        if (user_obj.isPresent()) {
            User existing_user = user_obj.get();
            Wishlist wishlist = existing_user.getWishlist();

            for (Book book : wishlist.getBooks()) {
                book.setAvailableCopies(book.getAvailableCopies() - 1);

                this.bookRepository.save(book);
            }

            wishlist.getBooks().clear();

            this.wishlistRepository.save(wishlist);

            return Optional.of(DisplayWishlistDTO.fromWishlistToDisplayWishlistDTO(List.of()));
        }

        return Optional.empty();
    }
}
