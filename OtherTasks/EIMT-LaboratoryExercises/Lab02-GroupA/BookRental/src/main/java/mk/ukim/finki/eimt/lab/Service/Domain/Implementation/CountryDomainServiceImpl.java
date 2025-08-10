package mk.ukim.finki.eimt.lab.Service.Domain.Implementation;

import mk.ukim.finki.eimt.lab.Model.Country;
import mk.ukim.finki.eimt.lab.Repository.CountryRepository;
import mk.ukim.finki.eimt.lab.Service.Domain.CountryDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryDomainServiceImpl implements CountryDomainService {

    private final CountryRepository countryRepository;

    public CountryDomainServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Country> findByID(Long ID) {
        return this.countryRepository.findById(ID);
    }

    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> create(Country country) {
        Country country_obj = this.countryRepository.save(country);

        return Optional.of(country_obj);
    }

    @Override
    public Optional<Country> update(Long ID, Country country) {
        Optional<Country> country_obj = this.countryRepository.findById(ID);

        if (country_obj.isPresent()) {
            Country existing_country = country_obj.get();
            existing_country.setName(country.getName());
            existing_country.setContinent(country.getContinent());

            this.countryRepository.save(existing_country);

            return Optional.of(existing_country);
        }

        return Optional.empty();
    }

    @Override
    public void delete(Long ID) {
        this.countryRepository.deleteById(ID);
    }
}
