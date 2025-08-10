package mk.ukim.finki.eimt.lab.DataHolder;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.eimt.lab.Model.Entities.Author;
import mk.ukim.finki.eimt.lab.Model.Entities.Book;
import mk.ukim.finki.eimt.lab.Model.Entities.Country;
import mk.ukim.finki.eimt.lab.Model.Entities.User;
import mk.ukim.finki.eimt.lab.Model.Enumerations.BookCategory;
import mk.ukim.finki.eimt.lab.Model.Enumerations.Role;
import mk.ukim.finki.eimt.lab.Repository.AuthorRepository;
import mk.ukim.finki.eimt.lab.Repository.BookRepository;
import mk.ukim.finki.eimt.lab.Repository.CountryRepository;
import mk.ukim.finki.eimt.lab.Repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class DataInitializr {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public DataInitializr(AuthorRepository authorRepository, CountryRepository countryRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

//    @PostConstruct
    public void initializeData() {
        // Тhe country is dependent on the author, it must be the first!
        Country country1 = new Country("Macedonia", "Europe");
        Country country2 = new Country("Brasil", "South America");
        Country country3 = new Country("Japan", "Asia");

        this.countryRepository.save(country1);
        this.countryRepository.save(country2);
        this.countryRepository.save(country3);

        Author author1 = new Author("Petre M.", "Andreevski", country1);
        Author author2 = new Author("Paulo", "Coelho", country2);
        Author author3 = new Author("Haruki", "Murakami", country3);

        this.authorRepository.save(author1);
        this.authorRepository.save(author2);
        this.authorRepository.save(author3);

        Book book1 = new Book("Pride and Prejudice", BookCategory.DRAMA, author1, 10);
        Book book2 = new Book("One Hundred Years of Solitude", BookCategory.FANTASY, author2, 5);
        Book book3 = new Book("The Little Prince", BookCategory.BIOGRAPHY, author3, 3);

        this.bookRepository.save(book1);
        this.bookRepository.save(book2);
        this.bookRepository.save(book3);

        this.userRepository.save(new User("ivan", "ivan", "ivan", "ivan", Role.USER));
        this.userRepository.save(new User("marija", "marija", "marija", "marija", Role.LIBRARIAN));
    }
}
