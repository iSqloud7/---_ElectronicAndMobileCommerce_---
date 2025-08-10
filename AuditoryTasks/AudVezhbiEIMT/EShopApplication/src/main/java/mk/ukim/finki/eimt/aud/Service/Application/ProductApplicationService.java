package mk.ukim.finki.eimt.aud.Service.Application;

import mk.ukim.finki.eimt.aud.Model.DTO.CreateProductDTO;
import mk.ukim.finki.eimt.aud.Model.DTO.DisplayProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductApplicationService {

    List<DisplayProductDTO> findAll();

    Optional<DisplayProductDTO> findByID(Long ID);

    Optional<DisplayProductDTO> create(CreateProductDTO createProductDTO);

    Optional<DisplayProductDTO> update(Long ID, CreateProductDTO createProductDTO);

    void deleteByID(Long ID);
}