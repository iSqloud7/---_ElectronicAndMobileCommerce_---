package mk.ukim.finki.eimt.lab.Service.Domain.Implementation;

import mk.ukim.finki.eimt.lab.Model.Entities.Book;
import mk.ukim.finki.eimt.lab.Model.Views.BooksPerAuthorView;
import mk.ukim.finki.eimt.lab.Repository.BookRepository;
import mk.ukim.finki.eimt.lab.Repository.BooksPerAuthorViewRepository;
import mk.ukim.finki.eimt.lab.Service.Domain.BookDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookDomainServiceImpl implements BookDomainService {

    private final BookRepository bookRepository;
    private final BooksPerAuthorViewRepository booksPerAuthorViewRepository;

    public BookDomainServiceImpl(BookRepository bookRepository, BooksPerAuthorViewRepository booksPerAuthorViewRepository) {
        this.bookRepository = bookRepository;
        this.booksPerAuthorViewRepository = booksPerAuthorViewRepository;
    }

    @Override
    public Optional<Book> findByID(Long ID) {
        return this.bookRepository.findById(ID);
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> create(Book book) {
        Book book_obj = this.bookRepository.save(book);

        return Optional.of(book_obj);
    }

    @Override
    public Optional<Book> update(Long ID, Book book) {
        Optional<Book> book_obj = this.bookRepository.findById(ID);

        if (book_obj.isPresent()) {
            Book existing_book = book_obj.get();
            existing_book.setName(book.getName());
            existing_book.setCategory(book.getCategory());
            existing_book.setAuthor(book.getAuthor());
            existing_book.setAvailableCopies(book.getAvailableCopies());

            this.bookRepository.save(existing_book);

            return Optional.of(existing_book);
        }

        return Optional.empty();
    }

    @Override
    public void delete(Long ID) {
        this.bookRepository.deleteById(ID);
    }

    @Override
    public Optional<Book> markBookAsRented(Long ID) {
        Optional<Book> book_obj = this.bookRepository.findById(ID);

        if (book_obj.isPresent()) {
            Book existing_book = book_obj.get();
            if (existing_book.getAvailableCopies() > 0) {
                existing_book.setAvailableCopies(existing_book.getAvailableCopies() - 1);

                this.bookRepository.save(existing_book);

                return Optional.of(existing_book);
            }
        }

        return Optional.empty();
    }

    @Override
    public void refreshMaterializedView() {
        this.booksPerAuthorViewRepository.refreshMaterializedView();
    }

    @Override
    public Optional<List<BooksPerAuthorView>> findBooksPerAuthor() {
        return Optional.of(this.booksPerAuthorViewRepository.findAll());
    }
}
