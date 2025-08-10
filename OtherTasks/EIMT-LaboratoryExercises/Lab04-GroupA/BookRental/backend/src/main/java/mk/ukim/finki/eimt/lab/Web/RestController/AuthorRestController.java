package mk.ukim.finki.eimt.lab.Web.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.eimt.lab.Model.DTO.AuthorDTO.CreateAuthorDTO;
import mk.ukim.finki.eimt.lab.Model.DTO.AuthorDTO.DisplayAuthorDTO;
import mk.ukim.finki.eimt.lab.Model.Projections.AuthorProjection;
import mk.ukim.finki.eimt.lab.Model.Views.AuthorsPerCountryView;
import mk.ukim.finki.eimt.lab.Service.Application.AuthorApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/authors")
@Tag(name = "Author API", description = "Endpoints for managing user authors.")
public class AuthorRestController {

    private final AuthorApplicationService authorApplicationService;

    public AuthorRestController(AuthorApplicationService authorApplicationService) {
        this.authorApplicationService = authorApplicationService;
    }

    @GetMapping("/{ID}")
    @Operation(summary = "Get author by ID!", description = "Finds an author by it's ID.")
    public ResponseEntity<DisplayAuthorDTO> findByID(@PathVariable Long ID) {
        return this.authorApplicationService.findByID(ID)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/")
    @Operation(summary = "Get all authors!", description = "Retrieves a list of all available authors.")
    public ResponseEntity<List<DisplayAuthorDTO>> findAll() {
        return ResponseEntity.ok(this.authorApplicationService.findAll());
    }

    @PostMapping("/add-author")
    @Operation(summary = "Add a new author!", description = "Creates a new author based on the given CreateAuthorDTO.")
    public ResponseEntity<DisplayAuthorDTO> addAuthor(@RequestBody CreateAuthorDTO createAuthorDTO) {
        return this.authorApplicationService.create(createAuthorDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit-author/{ID}")
    @Operation(summary = "Edit an existing author!", description = "Updates an author by ID.")
    public ResponseEntity<DisplayAuthorDTO> editAuthor(@PathVariable Long ID,
                                                       @RequestBody CreateAuthorDTO createAuthorDTO) {
        return this.authorApplicationService.update(ID, createAuthorDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete-author/{ID}")
    @Operation(summary = "Delete an author!", description = "Deletes an author by it's ID.")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long ID) {
        Optional<DisplayAuthorDTO> author_obj = this.authorApplicationService.findByID(ID);

        if (author_obj.isPresent()) {
            this.authorApplicationService.delete(ID);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-country")
    public ResponseEntity<List<AuthorsPerCountryView>> findAllAuthorsPerCountry() {
        return this.authorApplicationService.findAllAuthorsPerCountry()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/names")
    public ResponseEntity<List<AuthorProjection>> findAllAuthorsByNameAndSurname() {
        return this.authorApplicationService.findAllAuthorsByNameAndSurname()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
