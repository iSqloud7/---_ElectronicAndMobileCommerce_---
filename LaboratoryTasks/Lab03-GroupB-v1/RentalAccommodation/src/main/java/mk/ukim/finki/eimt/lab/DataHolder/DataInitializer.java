package mk.ukim.finki.eimt.lab.DataHolder;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.eimt.lab.Model.Domain.Booking;
import mk.ukim.finki.eimt.lab.Model.Domain.Country;
import mk.ukim.finki.eimt.lab.Model.Domain.User;
import mk.ukim.finki.eimt.lab.Model.Enumerations.BookingCategory;
import mk.ukim.finki.eimt.lab.Model.Domain.Host;
import mk.ukim.finki.eimt.lab.Model.Enumerations.UserRole;
import mk.ukim.finki.eimt.lab.Repository.BookingRepository;
import mk.ukim.finki.eimt.lab.Repository.CountryRepository;
import mk.ukim.finki.eimt.lab.Repository.HostRepository;
import mk.ukim.finki.eimt.lab.Repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;

    public DataInitializer(CountryRepository countryRepository, HostRepository hostRepository, BookingRepository bookingRepository, UserRepository userRepository) {
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void initializeData() {
        Country country1 = new Country("Macedonia", "Europe");
        Country country2 = new Country("Brasil", "South America");
        Country country3 = new Country("Japan", "Asia");

        this.countryRepository.save(country1);
        this.countryRepository.save(country2);
        this.countryRepository.save(country3);

        Host host1 = new Host("Ivan", "Pupinoski", country1);
        Host host2 = new Host("Lucas", "Oliveira", country2);
        Host host3 = new Host("Hiroshi", "Tanaka", country3);

        this.hostRepository.save(host1);
        this.hostRepository.save(host2);
        this.hostRepository.save(host3);

        Booking booking1 = new Booking("BOOKING_1", BookingCategory.ROOM, host1, 5);
        Booking booking2 = new Booking("BOOKING_2", BookingCategory.APARTMENT, host2, 1);
        Booking booking3 = new Booking("BOOKING_3", BookingCategory.HOTEL, host3, 3);

        this.bookingRepository.save(booking1);
        this.bookingRepository.save(booking2);
        this.bookingRepository.save(booking3);

        User user1 = new User("ivan", "ivan", "ivan", "ivan", UserRole.HOST);
        User user2 = new User("marija", "marija", "marija", "marija", UserRole.USER);
        User user3 = new User("sara", "sara", "sara", "sara", UserRole.USER);

        this.userRepository.save(user1);
        this.userRepository.save(user2);
        this.userRepository.save(user3);
    }
}
