package mk.ukim.finki.eimt.lab.Service.Application.Implementation;

import mk.ukim.finki.eimt.lab.Model.DTO.HostDTO.CreateHostDTO;
import mk.ukim.finki.eimt.lab.Model.DTO.HostDTO.DisplayHostDTO;
import mk.ukim.finki.eimt.lab.Model.Entities.Country;
import mk.ukim.finki.eimt.lab.Model.Projections.HostProjection;
import mk.ukim.finki.eimt.lab.Model.Views.HostsPerCountryView;
import mk.ukim.finki.eimt.lab.Service.Application.HostApplicationService;
import mk.ukim.finki.eimt.lab.Service.Domain.CountryDomainService;
import mk.ukim.finki.eimt.lab.Service.Domain.HostDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HostApplicationServiceImpl implements HostApplicationService {

    private final HostDomainService hostDomainService;
    private final CountryDomainService countryDomainService;

    public HostApplicationServiceImpl(HostDomainService hostService, CountryDomainService countryService) {
        this.hostDomainService = hostService;
        this.countryDomainService = countryService;
    }

    @Override
    public List<DisplayHostDTO> findAll() {
        return this.hostDomainService.findAll().stream().map(DisplayHostDTO::from).collect(Collectors.toList());
    }

    @Override
    public Optional<DisplayHostDTO> findByID(Long ID) {
        return this.hostDomainService.findByID(ID)
                .map(DisplayHostDTO::from);
    }

    @Override
    public Optional<DisplayHostDTO> create(CreateHostDTO createHostDTO) {
        Optional<Country> country_obj = this.countryDomainService.findByID(createHostDTO.countryID());

        if (country_obj.isPresent()) {
            return this.hostDomainService.create(createHostDTO.toHost(country_obj.get()))
                    .map(DisplayHostDTO::from);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<DisplayHostDTO> update(Long ID, CreateHostDTO createHostDTO) {
        Optional<Country> country_obj = this.countryDomainService.findByID(createHostDTO.countryID());

        if (country_obj.isPresent()) {
            return this.hostDomainService.update(ID, createHostDTO.toHost(country_obj.get()))
                    .map(DisplayHostDTO::from);
        }

        return Optional.empty();
    }

    @Override
    public void delete(Long ID) {
        this.hostDomainService.delete(ID);
    }

    @Override
    public Optional<List<HostsPerCountryView>> findAllHostsPerCountryView() {
        return this.hostDomainService.findAllHostsPerCountryView();
    }

    @Override
    public Optional<List<HostProjection>> findAllHostsWithProjection() {
        return this.hostDomainService.findAllHostsWithProjection();
    }
}
