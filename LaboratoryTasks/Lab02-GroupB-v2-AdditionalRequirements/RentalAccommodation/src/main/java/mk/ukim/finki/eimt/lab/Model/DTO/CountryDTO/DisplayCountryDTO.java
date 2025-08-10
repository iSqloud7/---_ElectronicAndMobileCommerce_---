package mk.ukim.finki.eimt.lab.Model.DTO.CountryDTO;

import mk.ukim.finki.eimt.lab.Model.Domain.Booking;
import mk.ukim.finki.eimt.lab.Model.Domain.Country;
import mk.ukim.finki.eimt.lab.Model.Domain.Host;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayCountryDTO(
        Long ID,
        String name,
        String continent
) {
    public static DisplayCountryDTO from(Country country) {
        return new DisplayCountryDTO(
                country.getID(),
                country.getName(),
                country.getContinent()
        );
    }

    public static List<DisplayCountryDTO> from(List<Country> countries) {
        return countries.stream()
                .map(DisplayCountryDTO::from)
                .collect(Collectors.toList());
    }

    public Country toCountry() {
        return new Country(ID, name, continent);
    }
}