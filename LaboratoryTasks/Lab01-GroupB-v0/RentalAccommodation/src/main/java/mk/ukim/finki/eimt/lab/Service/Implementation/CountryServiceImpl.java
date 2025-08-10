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
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }
}
