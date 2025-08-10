package mk.ukim.finki.eimt.lab.Service;

import mk.ukim.finki.eimt.lab.Model.Author;
import mk.ukim.finki.eimt.lab.Model.Book;
import mk.ukim.finki.eimt.lab.Model.BookCategory;
import mk.ukim.finki.eimt.lab.Model.DTO.BookDTO;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    Book create(BookDTO bookDTO) throws Exception;

    Book update(Long bookID, BookDTO bookDTO) throws Exception;

    void delete(Long ID);

    Book markAsRented(Long ID) throws Exception;
}
