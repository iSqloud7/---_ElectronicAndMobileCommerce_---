package mk.ukim.finki.eimt.lab.Service.Domain;

import mk.ukim.finki.eimt.lab.Model.Entities.Author;
import mk.ukim.finki.eimt.lab.Model.Projections.AuthorProjection;
import mk.ukim.finki.eimt.lab.Model.Views.AuthorsPerCountryView;

import java.util.List;
import java.util.Optional;

public interface AuthorDomainService {

    Optional<Author> findByID(Long ID);

    List<Author> findAll();

    Optional<Author> create(Author author);

    Optional<Author> update(Long ID, Author author);

    void delete(Long ID);

    void refreshMaterializedView();

    Optional<List<AuthorsPerCountryView>> findAllAuthorsPerCountry();

    Optional<List<AuthorProjection>> findAllAuthorsByNameAndSurname();
}
