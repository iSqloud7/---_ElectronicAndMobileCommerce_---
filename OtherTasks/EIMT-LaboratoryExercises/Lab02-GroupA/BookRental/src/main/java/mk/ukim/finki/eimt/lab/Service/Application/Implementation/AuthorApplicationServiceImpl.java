package mk.ukim.finki.eimt.lab.Service.Application.Implementation;

import mk.ukim.finki.eimt.lab.Model.Country;
import mk.ukim.finki.eimt.lab.Model.DTO.AuthorDTO.CreateAuthorDTO;
import mk.ukim.finki.eimt.lab.Model.DTO.AuthorDTO.DisplayAuthorDTO;
import mk.ukim.finki.eimt.lab.Service.Application.AuthorApplicationService;
import mk.ukim.finki.eimt.lab.Service.Domain.AuthorDomainService;
import mk.ukim.finki.eimt.lab.Service.Domain.CountryDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorApplicationServiceImpl implements AuthorApplicationService {

    private final AuthorDomainService authorDomainService;
    private final CountryDomainService countryDomainService;

    public AuthorApplicationServiceImpl(AuthorDomainService authorDomainService, CountryDomainService countryDomainService) {
        this.authorDomainService = authorDomainService;
        this.countryDomainService = countryDomainService;
    }

    @Override
    public Optional<DisplayAuthorDTO> findByID(Long ID) {
        return this.authorDomainService.findByID(ID)
                .map(DisplayAuthorDTO::fromAuthor);
    }

    @Override
    public List<DisplayAuthorDTO> findAll() {
        return this.authorDomainService.findAll().stream()
                .map(DisplayAuthorDTO::fromAuthor)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DisplayAuthorDTO> create(CreateAuthorDTO createAuthorDTO) {
        Optional<Country> country_obj = this.countryDomainService.findByID(createAuthorDTO.countryID());

        if (country_obj.isPresent()) {
            Country existing_country = country_obj.get();
            return this.authorDomainService.create(createAuthorDTO.toAuthor(existing_country))
                    .map(DisplayAuthorDTO::fromAuthor);
        }

        return Optional.empty();
    }

    @Override
    public Optional<DisplayAuthorDTO> update(Long ID, CreateAuthorDTO createAuthorDTO) {
        Optional<Country> country_obj = this.countryDomainService.findByID(ID);

        if (country_obj.isPresent()) {
            Country existing_country = country_obj.get();

            return this.authorDomainService.update(ID, createAuthorDTO.toAuthor(existing_country))
                    .map(DisplayAuthorDTO::fromAuthor);
        }
        return Optional.empty();
    }

    @Override
    public void delete(Long ID) {
        this.authorDomainService.delete(ID);
    }
}
