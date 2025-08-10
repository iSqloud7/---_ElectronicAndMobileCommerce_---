package mk.ukim.finki.eimt.lab.DataHolder;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.eimt.lab.Model.Author;
import mk.ukim.finki.eimt.lab.Model.Country;
import mk.ukim.finki.eimt.lab.Repository.AuthorRepository;
import mk.ukim.finki.eimt.lab.Repository.CountryRepository;
import org.springframework.stereotype.Component;

@Component
public class DataInitializr {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public DataInitializr(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @PostConstruct
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
    }
}
