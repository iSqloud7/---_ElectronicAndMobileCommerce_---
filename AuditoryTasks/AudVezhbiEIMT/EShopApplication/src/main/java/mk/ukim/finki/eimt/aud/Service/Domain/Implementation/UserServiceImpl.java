package mk.ukim.finki.eimt.aud.Service.Domain.Implementation;

import mk.ukim.finki.eimt.aud.Model.Entity.User;
import mk.ukim.finki.eimt.aud.Model.Enumerations.Role;
import mk.ukim.finki.eimt.aud.Model.Exceptions.*;
import mk.ukim.finki.eimt.aud.Repository.UserRepository;
import mk.ukim.finki.eimt.aud.Service.Domain.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Role role) {

        if (username == null || username.isEmpty() ||
                password == null || password.isEmpty()) {
            throw new InvalidUsernameOrPasswordException();
        }

        if (!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchException();
        }

        if (this.userRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException(username);
        }

        User user_obj = new User(username, passwordEncoder.encode(password), name, surname, role);

        return this.userRepository.save(user_obj);
    }

    @Override
    public User login(String username, String password) {

        if (username == null || username.isEmpty() ||
                password == null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }

        return this.userRepository.findByUsernameAndPassword(username, password).orElseThrow(() -> new InvalidUserCredentialsException());
    }
}