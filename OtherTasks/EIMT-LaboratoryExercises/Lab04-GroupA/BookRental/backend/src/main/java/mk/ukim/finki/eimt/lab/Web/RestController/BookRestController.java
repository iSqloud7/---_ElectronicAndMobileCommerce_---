package mk.ukim.finki.eimt.lab.Web.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.eimt.lab.Model.DTO.BookDTO.CreateBookDTO;
import mk.ukim.finki.eimt.lab.Model.DTO.BookDTO.DisplayBookDTO;
import mk.ukim.finki.eimt.lab.Model.Views.BooksPerAuthorView;
import mk.ukim.finki.eimt.lab.Service.Application.BookApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Book API", description = "Endpoints for managing user books.")
public class BookRestController {

    private final BookApplicationService bookApplicationService;

    public BookRestController(BookApplicationService bookApplicationService) {
        this.bookApplicationService = bookApplicationService;
    }

    @GetMapping("/{ID}")
    @Operation(summary = "Get book by ID!", description = "Finds a book by it's ID.")
    public ResponseEntity<DisplayBookDTO> findByID(@PathVariable Long ID) {
        return this.bookApplicationService.findByID(ID)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/")
    @Operation(summary = "Get all books!", description = "Retrieves a list of all available books.")
    public ResponseEntity<List<DisplayBookDTO>> findAll() {
        return ResponseEntity.ok(this.bookApplicationService.findAll());
    }

    @PostMapping("/add-book")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @Operation(summary = "Add a new book!", description = "Creates a new book based on the given CreateBookDTO.")
    public ResponseEntity<DisplayBookDTO> addBook(@RequestBody CreateBookDTO createBookDTO) {
        return this.bookApplicationService.create(createBookDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit-book/{ID}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @Operation(summary = "Edit an existing book!", description = "Updates a book by ID.")
    public ResponseEntity<DisplayBookDTO> editBook(@PathVariable Long ID,
                                                   @RequestBody CreateBookDTO createBookDTO) {
        return this.bookApplicationService.update(ID, createBookDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete-book/{ID}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @Operation(summary = "Delete a book!", description = "Deletes a book by it's ID.")
    public ResponseEntity<Void> deleteBook(@PathVariable Long ID) {
        Optional<DisplayBookDTO> book_obj = this.bookApplicationService.findByID(ID);

        if (book_obj.isPresent()) {
            this.bookApplicationService.delete(ID);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/rent-book/{ID}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @Operation(summary = "Mark a book as rented", description = "Marks a book as rented by its ID.")
    public ResponseEntity<DisplayBookDTO> markBookAsRented(@PathVariable Long ID) {
        return this.bookApplicationService.markBookAsRented(ID)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-author")
    public ResponseEntity<List<BooksPerAuthorView>> findBooksPerAuthor() {
        return this.bookApplicationService.findAllBooksPerAuthor()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}