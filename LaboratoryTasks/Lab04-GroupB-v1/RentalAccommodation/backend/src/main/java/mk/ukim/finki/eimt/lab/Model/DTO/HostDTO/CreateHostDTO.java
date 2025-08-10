package mk.ukim.finki.eimt.lab.Model.DTO.HostDTO;

import mk.ukim.finki.eimt.lab.Model.Entities.Country;
import mk.ukim.finki.eimt.lab.Model.Entities.Host;

import java.util.List;
import java.util.stream.Collectors;

public record CreateHostDTO(
        String name,
        String surname,
        Long countryID
) {
    public static CreateHostDTO from(Host host) {
        return new CreateHostDTO(
                host.getName(),
                host.getSurname(),
                host.getCountry().getID()
        );
    }

    public static List<CreateHostDTO> from(List<Host> hosts) {
        return hosts.stream()
                .map(CreateHostDTO::from)
                .collect(Collectors.toList());
    }

    public Host toHost(Country country) {
        return new Host(
                name,
                surname,
                country);
    }
}