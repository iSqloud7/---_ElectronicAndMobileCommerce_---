package mk.ukim.finki.eimt.lab.Web.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.eimt.lab.Model.DTO.CountryDTO.CreateCountryDTO;
import mk.ukim.finki.eimt.lab.Model.DTO.CountryDTO.DisplayCountryDTO;
import mk.ukim.finki.eimt.lab.Service.Application.CountryApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/countries")
@Tag(name = "Country API", description = "Endpoints for managing user countries.")
public class CountryRestController {

    private final CountryApplicationService countryApplicationService;

    public CountryRestController(CountryApplicationService countryApplicationService) {
        this.countryApplicationService = countryApplicationService;
    }

    @GetMapping("/{ID}")
    @Operation(summary = "Get country by ID!", description = "Finds a country by it's ID.")
    public ResponseEntity<DisplayCountryDTO> findByID(@PathVariable Long ID) {
        return this.countryApplicationService.findByID(ID)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/")
    @Operation(summary = "Get all countries!", description = "Retrieves a list of all available countries.")
    public ResponseEntity<List<DisplayCountryDTO>> findAll() {
        return ResponseEntity.ok(this.countryApplicationService.findAll());
    }

    @PostMapping("/add-country")
    @Operation(summary = "Add a new country!", description = "Creates a new country based on the given CreateCountryDTO.")
    public ResponseEntity<DisplayCountryDTO> addCountry(@RequestBody CreateCountryDTO createCountryDTO) {
        return this.countryApplicationService.create(createCountryDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit-country/{ID}")
    @Operation(summary = "Edit an existing country!", description = "Updates a country by ID.")
    public ResponseEntity<DisplayCountryDTO> editCountry(@PathVariable Long ID,
                                                         @RequestBody CreateCountryDTO createCountryDTO) {
        return this.countryApplicationService.update(ID, createCountryDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete-country/{ID}")
    @Operation(summary = "Delete a country!", description = "Deletes a country by it's ID.")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long ID) {
        Optional<DisplayCountryDTO> country_obj = this.countryApplicationService.findByID(ID);

        if (country_obj.isPresent()) {
            this.countryApplicationService.delete(ID);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
