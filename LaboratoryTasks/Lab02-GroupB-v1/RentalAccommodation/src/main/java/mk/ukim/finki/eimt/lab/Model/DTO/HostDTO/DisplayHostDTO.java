package mk.ukim.finki.eimt.lab.Model.DTO.HostDTO;

import mk.ukim.finki.eimt.lab.Model.DTO.CountryDTO.DisplayCountryDTO;
import mk.ukim.finki.eimt.lab.Model.Domain.Country;
import mk.ukim.finki.eimt.lab.Model.Domain.Host;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayHostDTO(
        Long ID,
        String name,
        String surname,
        Country country
) {
    public static DisplayHostDTO from(Host host) {
        return new DisplayHostDTO(
                host.getID(),
                host.getName(),
                host.getSurname(),
                host.getCountry()
        );
    }

    public static List<DisplayHostDTO> from(List<Host> hosts) {
        return hosts.stream()
                .map(DisplayHostDTO::from)
                .collect(Collectors.toList());
    }

    public Host toHost() {
        return new Host(ID, name, surname, country);
    }
}