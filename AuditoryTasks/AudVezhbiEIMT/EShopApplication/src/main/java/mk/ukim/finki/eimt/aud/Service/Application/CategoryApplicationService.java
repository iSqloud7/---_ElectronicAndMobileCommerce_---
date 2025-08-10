package mk.ukim.finki.eimt.aud.Service.Application;

import mk.ukim.finki.eimt.aud.Model.DTO.CreateCategoryDTO;
import mk.ukim.finki.eimt.aud.Model.DTO.DisplayCategoryDTO;

import java.util.List;
import java.util.Optional;

public interface CategoryApplicationService {

    List<DisplayCategoryDTO> findAll();

    Optional<DisplayCategoryDTO> findByID(Long ID);

    Optional<DisplayCategoryDTO> create(CreateCategoryDTO createCategoryDTO);

    Optional<DisplayCategoryDTO> update(Long ID, CreateCategoryDTO createCategoryDTO);

    void deleteByID(Long ID);
}