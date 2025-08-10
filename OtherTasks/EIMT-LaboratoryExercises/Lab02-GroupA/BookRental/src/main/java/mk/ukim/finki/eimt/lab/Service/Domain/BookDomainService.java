package mk.ukim.finki.eimt.lab.Service.Domain;

import mk.ukim.finki.eimt.lab.Model.Book;

import java.util.List;
import java.util.Optional;

public interface BookDomainService {

    Optional<Book> findByID(Long ID);

    List<Book> findAll();

    Optional<Book> create(Book book);

    Optional<Book> update(Long ID, Book book);

    void delete(Long ID);

    Optional<Book> markBookAsRented(Long ID);
}
