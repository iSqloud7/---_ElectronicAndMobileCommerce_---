package mk.ukim.finki.eimt.lab.Service.Domain.Implementation;

import mk.ukim.finki.eimt.lab.Model.Entities.Country;
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
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> findByID(Long ID) {
        return this.countryRepository.findById(ID);
    }
}
