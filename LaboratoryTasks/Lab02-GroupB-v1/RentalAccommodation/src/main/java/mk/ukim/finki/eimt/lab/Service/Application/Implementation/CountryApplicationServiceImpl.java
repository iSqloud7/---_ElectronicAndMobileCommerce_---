package mk.ukim.finki.eimt.lab.Service.Application.Implementation;

import mk.ukim.finki.eimt.lab.Model.Domain.Country;
import mk.ukim.finki.eimt.lab.Repository.CountryRepository;
import mk.ukim.finki.eimt.lab.Service.Application.CountryApplicationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {

    private final CountryRepository countryRepository;

    public CountryApplicationServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }
}
