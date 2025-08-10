package mk.ukim.finki.eimtprivatelessons.Service.Implementation;

import mk.ukim.finki.eimtprivatelessons.Model.User;
import mk.ukim.finki.eimtprivatelessons.Repository.UserRepository;
import mk.ukim.finki.eimtprivatelessons.Service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user_obj = this.userRepository.findByUsername(username);

        if (user_obj.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        // За да може да се врати објект, мора да бидат од ист тип:
        // I. Креирање објект од тип UserDetails.
        // II. Моделот User да го имплементира UserDetails интерфејсот.

        return user_obj.get();
    }
}
