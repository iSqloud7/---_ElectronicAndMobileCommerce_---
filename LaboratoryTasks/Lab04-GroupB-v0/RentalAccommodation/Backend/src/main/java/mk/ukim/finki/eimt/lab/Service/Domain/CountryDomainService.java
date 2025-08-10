package mk.ukim.finki.eimt.lab.Service.Domain;

import mk.ukim.finki.eimt.lab.Model.Entities.Country;

import java.util.List;
import java.util.Optional;

public interface CountryDomainService {

    List<Country> findAll();

    Optional<Country> findByID(Long ID);
}
