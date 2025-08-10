package mk.ukim.finki.eimt.lab.Service.Implementation;

import mk.ukim.finki.eimt.lab.Model.Country;
import mk.ukim.finki.eimt.lab.Repository.CountryRepository;
import mk.ukim.finki.eimt.lab.Service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAllCountries() {
        return this.countryRepository.findAll();
    }

    @Override
    public Country create(Country country) {
        Country country_obj = new Country(country.getName(), country.getContinent());

        return this.countryRepository.save(country_obj);
    }

    @Override
    public Country update(Long ID, Country country) throws Exception {
        Country country_obj = this.countryRepository.findById(ID).orElseThrow(Exception::new);

        country_obj.setName(country.getName());
        country_obj.setContinent(country.getContinent());

        return this.countryRepository.save(country_obj);
    }

    @Override
    public void delete(Long ID) {
        this.countryRepository.deleteById(ID);
    }
}
