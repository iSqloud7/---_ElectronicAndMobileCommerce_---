package mk.ukim.finki.eimt.lab.Service.Implementation;

import mk.ukim.finki.eimt.lab.Model.Author;
import mk.ukim.finki.eimt.lab.Model.Book;
import mk.ukim.finki.eimt.lab.Model.DTO.BookDTO;
import mk.ukim.finki.eimt.lab.Repository.AuthorRepository;
import mk.ukim.finki.eimt.lab.Repository.BookRepository;
import mk.ukim.finki.eimt.lab.Service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book create(BookDTO bookDTO) throws Exception {
        Author author_obj = this.authorRepository.findById(bookDTO.getAuthorID()).orElseThrow(Exception::new);
        Book book_obj = new Book(bookDTO.getName(), bookDTO.getCategory(), author_obj, bookDTO.getAvailableCopies());

        return this.bookRepository.save(book_obj);
    }

    @Override
    public Book update(Long bookID, BookDTO bookDTO) throws Exception {
        Book book_obj = this.bookRepository.findById(bookID).orElseThrow(Exception::new);
        Author author_obj = this.authorRepository.findById(bookDTO.getAuthorID()).orElseThrow(Exception::new);

        book_obj.setName(bookDTO.getName());
        book_obj.setCategory(bookDTO.getCategory());
        book_obj.setAuthor(author_obj);
        book_obj.setAvailableCopies(bookDTO.getAvailableCopies());

        return this.bookRepository.save(book_obj);
    }

    @Override
    public void delete(Long ID) {
        this.bookRepository.deleteById(ID);
    }

    @Override
    public Book markAsRented(Long ID) throws Exception {
        Book book_obj = this.bookRepository.findById(ID).orElseThrow(Exception::new);

        if (book_obj.getAvailableCopies() > 0) {
            book_obj.setAvailableCopies(book_obj.getAvailableCopies() - 1);

            return this.bookRepository.save(book_obj);
        }

        throw new RuntimeException("No copies available to rent!");
    }
}
