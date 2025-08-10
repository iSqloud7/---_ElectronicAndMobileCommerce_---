package mk.ukim.finki.eimt.aud.Service.Application;

import mk.ukim.finki.eimt.aud.Model.DTO.CreateManufacturerDTO;
import mk.ukim.finki.eimt.aud.Model.DTO.DisplayManufacturerDTO;

import java.util.List;
import java.util.Optional;

public interface ManufacturerApplicationService {

    List<DisplayManufacturerDTO> findAll();

    Optional<DisplayManufacturerDTO> findByID(Long ID);

    Optional<DisplayManufacturerDTO> create(CreateManufacturerDTO createManufacturerDTO);

    Optional<DisplayManufacturerDTO> update(Long ID, CreateManufacturerDTO createManufacturerDTO);

    void deleteByID(Long ID);
}