package mk.ukim.finki.eimt.lab.Service.Domain;

import mk.ukim.finki.eimt.lab.Model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryDomainService {

    Optional<Country> findByID(Long ID);

    List<Country> findAll();

    Optional<Country> create(Country country);

    Optional<Country> update(Long ID, Country country);

    void delete(Long ID);
}
