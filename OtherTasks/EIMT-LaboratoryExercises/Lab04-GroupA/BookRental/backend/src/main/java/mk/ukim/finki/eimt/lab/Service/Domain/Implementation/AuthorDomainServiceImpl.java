package mk.ukim.finki.eimt.lab.Service.Domain.Implementation;

import mk.ukim.finki.eimt.lab.Events.AuthorEvent;
import mk.ukim.finki.eimt.lab.Model.Entities.Author;
import mk.ukim.finki.eimt.lab.Model.Projections.AuthorProjection;
import mk.ukim.finki.eimt.lab.Model.Views.AuthorsPerCountryView;
import mk.ukim.finki.eimt.lab.Repository.AuthorRepository;
import mk.ukim.finki.eimt.lab.Repository.AuthorsPerCountryViewRepository;
import mk.ukim.finki.eimt.lab.Service.Domain.AuthorDomainService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorDomainServiceImpl implements AuthorDomainService {

    private final AuthorRepository authorRepository;
    private final AuthorsPerCountryViewRepository authorsPerCountryViewRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public AuthorDomainServiceImpl(AuthorRepository authorRepository, AuthorsPerCountryViewRepository authorsPerCountryViewRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.authorRepository = authorRepository;
        this.authorsPerCountryViewRepository = authorsPerCountryViewRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public Optional<Author> findByID(Long ID) {
        return this.authorRepository.findById(ID);
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> create(Author author) {
        Author author_obj = this.authorRepository.save(author);

        AuthorEvent authorEvent = new AuthorEvent(author);
        this.applicationEventPublisher.publishEvent(authorEvent);

        return Optional.of(author_obj);
    }

    @Override
    public Optional<Author> update(Long ID, Author author) {
        Optional<Author> author_obj = this.authorRepository.findById(ID);

        if (author_obj.isPresent()) {
            Author existing_author = author_obj.get();
            existing_author.setName(author.getName());
            existing_author.setSurname(author.getSurname());
            existing_author.setCountry(author.getCountry());

            this.authorRepository.save(existing_author);

            AuthorEvent authorEvent = new AuthorEvent(author);
            this.applicationEventPublisher.publishEvent(authorEvent);

            return Optional.of(existing_author);
        }
        return Optional.empty();
    }

    @Override
    public void delete(Long ID) {
        this.authorRepository.deleteById(ID);
        AuthorEvent authorEvent = new AuthorEvent(null);
        this.applicationEventPublisher.publishEvent(authorEvent);
    }

    @Override
    public void refreshMaterializedView() {
        this.authorsPerCountryViewRepository.refreshMaterializedView();
    }

    @Override
    public Optional<List<AuthorsPerCountryView>> findAllAuthorsPerCountry() {
        return Optional.of(this.authorsPerCountryViewRepository.findAll());
    }

    @Override
    public Optional<List<AuthorProjection>> findAllAuthorsByNameAndSurname() {
        return Optional.of(this.authorRepository.findAllByNameAndSurname());
    }
}
