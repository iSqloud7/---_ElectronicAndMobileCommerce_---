package mk.ukim.finki.eimt.lab.Service.Domain.Implementation;

import mk.ukim.finki.eimt.lab.Model.DTO.UserDTO.CreateUserDTO;
import mk.ukim.finki.eimt.lab.Model.DTO.UserDTO.DisplayUserDTO;
import mk.ukim.finki.eimt.lab.Model.Exception.UserNotFoundException;
import mk.ukim.finki.eimt.lab.Model.Entities.User;
import mk.ukim.finki.eimt.lab.Model.Entities.Wishlist;
import mk.ukim.finki.eimt.lab.Repository.UserRepository;
import mk.ukim.finki.eimt.lab.Repository.WishlistRepository;
import mk.ukim.finki.eimt.lab.Service.Domain.UserDomainService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDomainServiceImpl implements UserDomainService {

    private final UserRepository userRepository;
    private final WishlistRepository wishlistRepository;

    public UserDomainServiceImpl(UserRepository userRepository, WishlistRepository wishlistRepository) {
        this.userRepository = userRepository;
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public void register(CreateUserDTO createUserDTO) {

        if (createUserDTO.username() == null || createUserDTO.username().isEmpty() ||
                createUserDTO.password() == null || createUserDTO.password().isEmpty()) {
            throw new IllegalArgumentException("Username and password cannot be empty");
        }

        Wishlist wishlist = new Wishlist();

        this.wishlistRepository.save(wishlist);

        User user = createUserDTO.toUser();
        user.setWishlist(wishlist);

        this.userRepository.save(user);
    }

    @Override
    public Optional<DisplayUserDTO> login(String username, String password) {

        if (username == null || username.isEmpty() ||
                password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Username and password cannot be empty");
        }

        /*
        return userRepository.findByUsername(username)
                .filter(user -> user.getPassword().equals(password))
                .map(DisplayUserDTO::fromUser);
        */

        Optional<User> user = this.userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }

        if (!user.get().getPassword().equals(password)) {
            throw new UserNotFoundException();
        }

        return Optional.of(DisplayUserDTO.fromUser(user.get()));
    }
}
