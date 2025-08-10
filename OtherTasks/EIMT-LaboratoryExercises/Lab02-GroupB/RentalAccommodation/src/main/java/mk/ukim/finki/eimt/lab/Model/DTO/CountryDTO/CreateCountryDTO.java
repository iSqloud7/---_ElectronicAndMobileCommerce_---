package mk.ukim.finki.eimt.lab.Model.DTO.CountryDTO;

import mk.ukim.finki.eimt.lab.Model.DTO.BookingDTO.CreateBookingDTO;
import mk.ukim.finki.eimt.lab.Model.Domain.Booking;
import mk.ukim.finki.eimt.lab.Model.Domain.Country;

import java.util.List;
import java.util.stream.Collectors;

public record CreateCountryDTO(
        String name,
        String continent
) {
    public static CreateCountryDTO from(Country country) {
        return new CreateCountryDTO(
                country.getName(),
                country.getContinent()
        );
    }

    public static List<CreateCountryDTO> from(List<Country> countries) {
        return countries.stream()
                .map(CreateCountryDTO::from)
                .collect(Collectors.toList());
    }

    public Country toCountry() {
        return new Country(name, continent);
    }
}