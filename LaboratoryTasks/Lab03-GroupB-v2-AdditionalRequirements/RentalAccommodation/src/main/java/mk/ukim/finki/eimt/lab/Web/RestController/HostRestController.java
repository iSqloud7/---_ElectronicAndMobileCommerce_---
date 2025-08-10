package mk.ukim.finki.eimt.lab.Web.RestController;

import io.swagger.v3.oas.annotations.Operation;
import mk.ukim.finki.eimt.lab.Model.DTO.HostDTO.CreateHostDTO;
import mk.ukim.finki.eimt.lab.Model.DTO.HostDTO.DisplayHostDTO;
import mk.ukim.finki.eimt.lab.Model.Projections.HostProjection;
import mk.ukim.finki.eimt.lab.Model.Views.HostsPerCountryView;
import mk.ukim.finki.eimt.lab.Service.Application.HostApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hosts")
public class  HostRestController {

    private final HostApplicationService hostApplicationService;

    public HostRestController(HostApplicationService hostApplicationService) {
        this.hostApplicationService = hostApplicationService;
    }

    @Operation(summary = "Get all hosts!", description = "Retrieves a list of all available hosts.")
    @GetMapping
    public ResponseEntity<List<DisplayHostDTO>> findAll() {
        return ResponseEntity.ok(this.hostApplicationService.findAll());
    }

    @GetMapping("/{ID}")
    @Operation(summary = "Get host by ID!", description = "Finds a host by it's ID.")
    public ResponseEntity<DisplayHostDTO> findByID(@PathVariable Long ID) {
        return this.hostApplicationService.findByID(ID)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add-host")
    @Operation(summary = "Add a new host!", description = "Creates a new host based on the given CreateHostDTO.")
    public ResponseEntity<DisplayHostDTO> addHost(@RequestBody CreateHostDTO createHostDTO) {
        // return ResponseEntity.ok(this.hostApplicationService.create(createHostDTO).get());
        return this.hostApplicationService.create(createHostDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit-host/{ID}")
    @Operation(summary = "Update an existing host!", description = "Updates a host by ID.")
    public ResponseEntity<DisplayHostDTO> editHost(@PathVariable Long ID,
                                                   @RequestBody CreateHostDTO createHostDTO) {
        return this.hostApplicationService.update(ID, createHostDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete-host/{ID}")
    @Operation(summary = "Delete a host!", description = "Deletes a host by it's ID.")
    public ResponseEntity<Void> deleteHost(@PathVariable Long ID) {
        Optional<DisplayHostDTO> host_obj = this.hostApplicationService.findByID(ID);

        if (host_obj.isPresent()) {
            this.hostApplicationService.delete(ID);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-country")
    public Optional<ResponseEntity<List<HostsPerCountryView>>> findAllHostsPerCountryView() {
        return Optional.of(this.hostApplicationService.findAllHostsPerCountryView()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()));
    }

    @GetMapping("/names")
    public Optional<ResponseEntity<List<HostProjection>>> findAllHostsWithProjection() {
        return Optional.of(this.hostApplicationService.findAllHostsWithProjection()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()));
    }
}
