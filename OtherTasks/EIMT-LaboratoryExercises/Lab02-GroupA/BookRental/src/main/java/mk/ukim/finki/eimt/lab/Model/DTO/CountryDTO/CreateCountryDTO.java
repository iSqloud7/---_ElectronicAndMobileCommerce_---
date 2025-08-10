package mk.ukim.finki.eimt.lab.Model.DTO.CountryDTO;

import mk.ukim.finki.eimt.lab.Model.Country;

import java.util.List;
import java.util.stream.Collectors;

public record CreateCountryDTO(
        String name,
        String continent
) {
    public static CreateCountryDTO fromCountry(Country country) {
        return new CreateCountryDTO(
                country.getName(),
                country.getContinent()
        );
    }

    public static List<CreateCountryDTO> fromCountries(List<Country> countries) {
        return countries.stream()
                .map(CreateCountryDTO::fromCountry)
                .collect(Collectors.toList());
    }

    public Country toCountry() {
        return new Country(name, continent);
    }
}
