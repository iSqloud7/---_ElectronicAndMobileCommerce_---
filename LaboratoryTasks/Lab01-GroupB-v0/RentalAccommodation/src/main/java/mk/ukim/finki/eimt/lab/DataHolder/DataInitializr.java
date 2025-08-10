package mk.ukim.finki.eimt.lab.DataHolder;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.eimt.lab.Model.Country;
import mk.ukim.finki.eimt.lab.Model.Host;
import mk.ukim.finki.eimt.lab.Repository.CountryRepository;
import mk.ukim.finki.eimt.lab.Repository.HostRepository;
import org.springframework.stereotype.Component;

@Component
public class DataInitializr {

    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;

    public DataInitializr(CountryRepository countryRepository, HostRepository hostRepository) {
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
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
    }
}
