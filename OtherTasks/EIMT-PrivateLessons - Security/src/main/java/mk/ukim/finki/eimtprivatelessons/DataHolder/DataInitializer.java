package mk.ukim.finki.eimtprivatelessons.DataHolder;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.eimtprivatelessons.Model.Enumerations.UserRole;
import mk.ukim.finki.eimtprivatelessons.Model.User;
import mk.ukim.finki.eimtprivatelessons.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initializeData(){
        User user1 = new User("ivan", this.passwordEncoder.encode("ivan"), UserRole.ADMIN);

        this.userRepository.save(user1);
    }
}
