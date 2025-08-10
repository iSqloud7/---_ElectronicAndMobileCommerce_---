package mk.ukim.finki.eimt.lab.Service.Application;

import mk.ukim.finki.eimt.lab.Model.DTO.CountryDTO.CreateCountryDTO;
import mk.ukim.finki.eimt.lab.Model.DTO.CountryDTO.DisplayCountryDTO;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {

    Optional<DisplayCountryDTO> findByID(Long ID);

    List<DisplayCountryDTO> findAll();

    Optional<DisplayCountryDTO> create(CreateCountryDTO createCountryDTO);

    Optional<DisplayCountryDTO> update(Long ID, CreateCountryDTO createCountryDTO);

    void delete(Long ID);
}
