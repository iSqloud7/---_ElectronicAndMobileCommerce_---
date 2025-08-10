package mk.ukim.finki.eimt.lab.Service.Application.Implementation;

import mk.ukim.finki.eimt.lab.Model.Entities.Author;
import mk.ukim.finki.eimt.lab.Model.DTO.BookDTO.CreateBookDTO;
import mk.ukim.finki.eimt.lab.Model.DTO.BookDTO.DisplayBookDTO;
import mk.ukim.finki.eimt.lab.Model.Views.BooksPerAuthorView;
import mk.ukim.finki.eimt.lab.Service.Application.BookApplicationService;
import mk.ukim.finki.eimt.lab.Service.Domain.AuthorDomainService;
import mk.ukim.finki.eimt.lab.Service.Domain.BookDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookApplicationServiceImpl implements BookApplicationService {

    private final BookDomainService bookDomainService;
    private final AuthorDomainService authorDomainService;

    public BookApplicationServiceImpl(BookDomainService bookDomainService, AuthorDomainService authorDomainService) {
        this.bookDomainService = bookDomainService;
        this.authorDomainService = authorDomainService;
    }

    @Override
    public Optional<DisplayBookDTO> findByID(Long ID) {
        return this.bookDomainService.findByID(ID)
                .map(DisplayBookDTO::fromBook);
    }

    @Override
    public List<DisplayBookDTO> findAll() {
        return this.bookDomainService.findAll().stream()
                .map(DisplayBookDTO::fromBook)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DisplayBookDTO> create(CreateBookDTO createBookDTO) {
        Optional<Author> author_obj = this.authorDomainService.findByID(createBookDTO.authorID());

        if (author_obj.isPresent()) {
            Author existing_author = author_obj.get();

            return this.bookDomainService.create(createBookDTO.toBook(existing_author))
                    .map(DisplayBookDTO::fromBook);
        }

        return Optional.empty();
    }

    @Override
    public Optional<DisplayBookDTO> update(Long ID, CreateBookDTO createBookDTO) {
        Optional<Author> author_obj = this.authorDomainService.findByID(createBookDTO.authorID());

        if (author_obj.isPresent()) {
            Author existing_author = author_obj.get();

            return this.bookDomainService.update(ID, createBookDTO.toBook(existing_author))
                    .map(DisplayBookDTO::fromBook);
        }

        return Optional.empty();
    }

    @Override
    public void delete(Long ID) {
        this.bookDomainService.delete(ID);
    }

    @Override
    public Optional<DisplayBookDTO> markBookAsRented(Long ID) {
        return this.bookDomainService.markBookAsRented(ID)
                .map(DisplayBookDTO::fromBook);
    }

    @Override
    public Optional<List<BooksPerAuthorView>> findAllBooksPerAuthor() {
        return this.bookDomainService.findBooksPerAuthor();
    }
}
