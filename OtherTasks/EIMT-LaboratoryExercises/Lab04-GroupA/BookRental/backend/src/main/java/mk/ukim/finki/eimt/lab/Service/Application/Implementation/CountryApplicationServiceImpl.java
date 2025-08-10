package mk.ukim.finki.eimt.lab.Service.Application.Implementation;

import mk.ukim.finki.eimt.lab.Model.DTO.CountryDTO.CreateCountryDTO;
import mk.ukim.finki.eimt.lab.Model.DTO.CountryDTO.DisplayCountryDTO;
import mk.ukim.finki.eimt.lab.Service.Application.CountryApplicationService;
import mk.ukim.finki.eimt.lab.Service.Domain.CountryDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {

    private final CountryDomainService countryDomainService;

    public CountryApplicationServiceImpl(CountryDomainService countryDomainService) {
        this.countryDomainService = countryDomainService;
    }

    @Override
    public Optional<DisplayCountryDTO> findByID(Long ID) {
        return this.countryDomainService.findByID(ID)
                .map(DisplayCountryDTO::fromCountry);
    }

    @Override
    public List<DisplayCountryDTO> findAll() {
        return this.countryDomainService.findAll().stream()
                .map(DisplayCountryDTO::fromCountry)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DisplayCountryDTO> create(CreateCountryDTO createCountryDTO) {
        return this.countryDomainService.create(createCountryDTO.toCountry())
                .map(DisplayCountryDTO::fromCountry);
    }

    @Override
    public Optional<DisplayCountryDTO> update(Long ID, CreateCountryDTO createCountryDTO) {
        return this.countryDomainService.update(ID, createCountryDTO.toCountry())
                .map(DisplayCountryDTO::fromCountry);
    }

    @Override
    public void delete(Long ID) {
        this.countryDomainService.delete(ID);
    }
}
