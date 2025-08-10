package mk.ukim.finki.eimt.lab.Service.Application;

import mk.ukim.finki.eimt.lab.Model.DTO.BookDTO.CreateBookDTO;
import mk.ukim.finki.eimt.lab.Model.DTO.BookDTO.DisplayBookDTO;

import java.util.List;
import java.util.Optional;

public interface BookApplicationService {

    Optional<DisplayBookDTO> findByID(Long ID);

    List<DisplayBookDTO> findAll();

    Optional<DisplayBookDTO> create(CreateBookDTO createBookDTO);

    Optional<DisplayBookDTO> update(Long ID, CreateBookDTO createBookDTO);

    void delete(Long ID);

    Optional<DisplayBookDTO> markBookAsRented(Long ID);
}
