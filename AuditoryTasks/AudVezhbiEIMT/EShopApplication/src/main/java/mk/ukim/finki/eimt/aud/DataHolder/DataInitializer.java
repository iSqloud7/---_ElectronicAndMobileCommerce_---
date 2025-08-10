package mk.ukim.finki.eimt.aud.DataHolder;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.eimt.aud.Model.Entity.Category;
import mk.ukim.finki.eimt.aud.Model.Entity.Manufacturer;
import mk.ukim.finki.eimt.aud.Model.Entity.User;
import mk.ukim.finki.eimt.aud.Model.Enumerations.Role;
import mk.ukim.finki.eimt.aud.Repository.CategoryRepository;
import mk.ukim.finki.eimt.aud.Repository.ManufacturerRepository;
import mk.ukim.finki.eimt.aud.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final CategoryRepository categoryRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(CategoryRepository categoryRepository, ManufacturerRepository manufacturerRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.categoryRepository = categoryRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initializeData() {
        categoryRepository.save(new Category("Sports", "Sports category"));
        categoryRepository.save(new Category("Food", "Food category"));
        categoryRepository.save(new Category("Music", "Music category"));

        manufacturerRepository.save(new Manufacturer("Nike", "USA"));
        manufacturerRepository.save(new Manufacturer("KFC", "USA"));
        manufacturerRepository.save(new Manufacturer("A Records", "UK"));

        userRepository.save(new User(
                "emt",
                passwordEncoder.encode("emt"),
                "Ivan",
                "Pupinoski",
                Role.ROLE_ADMIN
        ));
    }
}