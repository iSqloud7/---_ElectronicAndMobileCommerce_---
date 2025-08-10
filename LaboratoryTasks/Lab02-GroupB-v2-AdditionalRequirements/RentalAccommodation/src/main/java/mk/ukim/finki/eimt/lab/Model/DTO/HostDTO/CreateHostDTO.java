package mk.ukim.finki.eimt.lab.Model.DTO.HostDTO;

import mk.ukim.finki.eimt.lab.Model.DTO.CountryDTO.CreateCountryDTO;
import mk.ukim.finki.eimt.lab.Model.Domain.Country;
import mk.ukim.finki.eimt.lab.Model.Domain.Host;

import java.util.List;
import java.util.stream.Collectors;

public record CreateHostDTO(
        String name,
        String surname,
        Country country
) {
    public static CreateHostDTO from(Host host) {
        return new CreateHostDTO(
                host.getName(),
                host.getSurname(),
                host.getCountry()
        );
    }

    public static List<CreateHostDTO> from(List<Host> hosts) {
        return hosts.stream()
                .map(CreateHostDTO::from)
                .collect(Collectors.toList());
    }

    public Host toHost() {
        return new Host(name, surname, country);
    }
}