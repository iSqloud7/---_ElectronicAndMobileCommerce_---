package mk.ukim.finki.eimt.lab.Model.DTO.CountryDTO;

import mk.ukim.finki.eimt.lab.Model.Entities.Country;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayCountryDTO(
        Long countryID,
        String name,
        String continent
) {
    public static DisplayCountryDTO fromCountry(Country country) {
        return new DisplayCountryDTO(
                country.getID(),
                country.getName(),
                country.getContinent()
        );
    }

    public static List<DisplayCountryDTO> fromCountries(List<Country> countries) {
        return countries.stream()
                .map(DisplayCountryDTO::fromCountry)
                .collect(Collectors.toList());
    }

    public Country toCountry() {
        return new Country(countryID, name, continent);
    }
}
