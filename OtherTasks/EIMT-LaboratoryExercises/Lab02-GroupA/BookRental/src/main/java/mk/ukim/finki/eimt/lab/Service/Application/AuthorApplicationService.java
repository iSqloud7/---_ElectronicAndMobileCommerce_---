package mk.ukim.finki.eimt.lab.Service.Application;

import mk.ukim.finki.eimt.lab.Model.Author;
import mk.ukim.finki.eimt.lab.Model.DTO.AuthorDTO.CreateAuthorDTO;
import mk.ukim.finki.eimt.lab.Model.DTO.AuthorDTO.DisplayAuthorDTO;

import java.util.List;
import java.util.Optional;

public interface AuthorApplicationService {

    Optional<DisplayAuthorDTO> findByID(Long ID);

    List<DisplayAuthorDTO> findAll();

    Optional<DisplayAuthorDTO> create(CreateAuthorDTO createAuthorDTO);

    Optional<DisplayAuthorDTO> update(Long ID, CreateAuthorDTO createAuthorDTO);

    void delete(Long ID);
}
