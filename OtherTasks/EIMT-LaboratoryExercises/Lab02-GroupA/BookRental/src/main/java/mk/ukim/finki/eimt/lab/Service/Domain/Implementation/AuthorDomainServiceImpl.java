package mk.ukim.finki.eimt.lab.Service.Domain.Implementation;

import mk.ukim.finki.eimt.lab.Model.Author;
import mk.ukim.finki.eimt.lab.Repository.AuthorRepository;
import mk.ukim.finki.eimt.lab.Service.Domain.AuthorDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorDomainServiceImpl implements AuthorDomainService {

    private final AuthorRepository authorRepository;

    public AuthorDomainServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
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

            return Optional.of(existing_author);
        }
        return Optional.empty();
    }

    @Override
    public void delete(Long ID) {
        this.authorRepository.deleteById(ID);
    }
}
