package mk.ukim.finki.eimt.lab.Web.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import mk.ukim.finki.eimt.lab.Model.Book;
import mk.ukim.finki.eimt.lab.Model.DTO.BookDTO;
import mk.ukim.finki.eimt.lab.Service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@SecurityRequirement(name = "Authentication")
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping // /api/books
    public ResponseEntity<List<Book>> findAll() {
        return ResponseEntity.ok(this.bookService.findAll());
    }

    @PostMapping("/add-book")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<Book> addBook(@RequestBody BookDTO bookDTO) throws Exception {
        return ResponseEntity.ok(this.bookService.create(bookDTO));
    }

    @PutMapping("/edit-book/{ID}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<Book> editBook(@PathVariable Long ID,
                                         @RequestBody BookDTO bookDTO) throws Exception {
        return ResponseEntity.ok(this.bookService.update(ID, bookDTO));
    }

    @DeleteMapping("/delete-book/{ID}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<Void> deleteBook(@PathVariable Long ID) {
        this.bookService.delete(ID);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/rent-book/{ID}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<Book> markAsRented(@PathVariable Long ID) throws Exception {
        return ResponseEntity.ok(this.bookService.markAsRented(ID));
    }
}