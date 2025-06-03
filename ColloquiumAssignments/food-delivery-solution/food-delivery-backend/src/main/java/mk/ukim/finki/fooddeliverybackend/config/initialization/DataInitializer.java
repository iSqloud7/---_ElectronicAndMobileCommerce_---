package mk.ukim.finki.fooddeliverybackend.config.initialization;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.fooddeliverybackend.model.domain.Dish;
import mk.ukim.finki.fooddeliverybackend.model.domain.Restaurant;
import mk.ukim.finki.fooddeliverybackend.model.domain.User;
import mk.ukim.finki.fooddeliverybackend.model.enums.Role;
import mk.ukim.finki.fooddeliverybackend.repository.DishRepository;
import mk.ukim.finki.fooddeliverybackend.repository.RestaurantRepository;
import mk.ukim.finki.fooddeliverybackend.repository.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Profile("!test")
@Component
public class DataInitializer {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final DishRepository dishRepository;

    public DataInitializer(
            PasswordEncoder passwordEncoder,
            UserRepository userRepository,
            RestaurantRepository restaurantRepository,
            DishRepository dishRepository
    ) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.dishRepository = dishRepository;
    }

    @PostConstruct
    public void init() {
        User customer = userRepository.save(new User(
                "customer",
                passwordEncoder.encode("customer"),
                "customer",
                "customer",
                "customer@email.com",
                Role.ROLE_CUSTOMER
        ));

        User owner = userRepository.save(new User(
                "owner",
                passwordEncoder.encode("owner"),
                "owner",
                "owner",
                "owner@email.com",
                Role.ROLE_OWNER
        ));

        User admin = userRepository.save(new User(
                "admin",
                passwordEncoder.encode("admin"),
                "admin",
                "admin",
                "admin@email.com",
                Role.ROLE_ADMIN
        ));

        Restaurant greenGarden = restaurantRepository.save(new Restaurant(
                "The Green Garden",
                "A vegetarian and vegan-friendly restaurant offering fresh, organic meals and locally-sourced produce."
        ));
        Restaurant sushiWorld = restaurantRepository.save(new Restaurant(
                "Sushi World",
                "Authentic Japanese cuisine specializing in sushi, sashimi, and creative rolls made by expert chefs."
        ));
        Restaurant pastaPalace = restaurantRepository.save(new Restaurant(
                "Pasta Palace",
                "A cozy Italian spot known for its handmade pasta, rich sauces, and rustic charm."
        ));
        Restaurant spiceSymphony = restaurantRepository.save(new Restaurant(
                "Spice Symphony",
                "An Indian restaurant offering a symphony of spices with classic curries, tandoori specialties, and biryani."
        ));
        Restaurant burgerBarn = restaurantRepository.save(new Restaurant(
                "Burger Barn",
                "Casual American dining with gourmet burgers, loaded fries, and thick milkshakes."
        ));
        Restaurant tacoFiesta = restaurantRepository.save(new Restaurant(
                "Taco Fiesta",
                "A vibrant Mexican eatery serving tacos, burritos, and enchiladas with bold flavors and fresh ingredients."
        ));
        Restaurant dragonWok = restaurantRepository.save(new Restaurant(
                "Dragon Wok",
                "Traditional Chinese food with modern twists, offering dim sum, stir-fries, and noodle dishes."
        ));
        Restaurant mediterranean = restaurantRepository.save(new Restaurant(
                "Mediterranean",
                "Fine Mediterranean dining with dishes inspired by Greek, Turkish, and Lebanese cuisines."
        ));
        Restaurant oceansCatch = restaurantRepository.save(new Restaurant(
                "Ocean's Catch",
                "A seafood grill specializing in fresh catches, grilled platters, and seafood pasta."
        ));
        Restaurant sweetToothBakery = restaurantRepository.save(new Restaurant(
                "Sweet Tooth Bakery",
                "A delightful bakery and café with a wide selection of cakes, pastries, and artisan coffee."
        ));

        // Green Garden
        dishRepository.save(new Dish("Vegan Buddha Bowl", "A bowl of quinoa, chickpeas, veggies, and tahini.", 9.99, 50, greenGarden));
        dishRepository.save(new Dish("Avocado Toast", "Whole grain toast topped with smashed avocado and microgreens.", 6.99, 40, greenGarden));

        // Sushi World
        dishRepository.save(new Dish("California Roll", "Crab, avocado, and cucumber rolled in seaweed and rice.", 8.50, 100, sushiWorld));
        dishRepository.save(new Dish("Salmon Sashimi", "Fresh sliced salmon served with soy and wasabi.", 12.00, 70, sushiWorld));

        // Pasta Palace
        dishRepository.save(new Dish("Spaghetti Carbonara", "Classic pasta with pancetta, egg, and parmesan.", 11.99, 60, pastaPalace));
        dishRepository.save(new Dish("Lasagna", "Layers of pasta, beef ragu, and creamy béchamel.", 13.50, 45, pastaPalace));

        // Spice Symphony
        dishRepository.save(new Dish("Chicken Tikka Masala", "Tender chicken in creamy spiced tomato sauce.", 10.99, 80, spiceSymphony));
        dishRepository.save(new Dish("Vegetable Biryani", "Fragrant rice with mixed vegetables and spices.", 9.50, 70, spiceSymphony));

        // Burger Barn
        dishRepository.save(new Dish("Classic Cheeseburger", "Beef patty with cheddar, lettuce, tomato, and pickles.", 9.99, 120, burgerBarn));
        dishRepository.save(new Dish("Bacon BBQ Burger", "Burger with crispy bacon, BBQ sauce, and onion rings.", 11.50, 90, burgerBarn));

        // Taco Fiesta
        dishRepository.save(new Dish("Chicken Tacos", "Soft tacos with grilled chicken, salsa, and cheese.", 7.99, 100, tacoFiesta));
        dishRepository.save(new Dish("Beef Burrito", "Flour tortilla stuffed with seasoned beef, beans, and rice.", 9.25, 80, tacoFiesta));

        // Dragon Wok
        dishRepository.save(new Dish("Kung Pao Chicken", "Spicy stir-fry with chicken, peanuts, and veggies.", 10.50, 60, dragonWok));
        dishRepository.save(new Dish("Vegetable Spring Rolls", "Crispy rolls filled with mixed vegetables.", 5.50, 150, dragonWok));

        // Mediterranean
        dishRepository.save(new Dish("Grilled Lamb Kebabs", "Tender lamb skewers with tzatziki sauce.", 14.00, 50, mediterranean));
        dishRepository.save(new Dish("Greek Salad", "Salad with feta, olives, cucumber, and tomatoes.", 7.00, 80, mediterranean));

        // Ocean's Catch
        dishRepository.save(new Dish("Grilled Salmon", "Fresh salmon fillet with lemon butter sauce.", 16.99, 40, oceansCatch));
        dishRepository.save(new Dish("Shrimp Alfredo", "Pasta with creamy Alfredo sauce and shrimp.", 15.50, 55, oceansCatch));

        // Sweet Tooth Bakery
        dishRepository.save(new Dish("Chocolate Cake", "Rich chocolate cake with ganache frosting.", 5.00, 90, sweetToothBakery));
        dishRepository.save(new Dish("Blueberry Muffin", "Moist muffin packed with fresh blueberries.", 3.50, 120, sweetToothBakery));
    }

}
