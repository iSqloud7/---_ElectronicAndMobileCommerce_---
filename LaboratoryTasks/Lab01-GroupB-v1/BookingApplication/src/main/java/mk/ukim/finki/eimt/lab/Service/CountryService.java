package mk.ukim.finki.eimt.lab.Service;

import mk.ukim.finki.eimt.lab.Model.Country;

import java.util.List;

public interface CountryService {

    List<Country> findAllCountries();

    Country create(Country country);

    Country update(Long ID, Country country) throws Exception;

    void delete(Long ID);
}
