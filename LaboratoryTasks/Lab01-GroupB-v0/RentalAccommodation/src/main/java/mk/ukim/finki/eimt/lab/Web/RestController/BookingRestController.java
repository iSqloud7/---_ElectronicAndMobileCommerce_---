package mk.ukim.finki.eimt.lab.Web.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import mk.ukim.finki.eimt.lab.Model.Booking;
import mk.ukim.finki.eimt.lab.Model.DTO.BookingDTO;
import mk.ukim.finki.eimt.lab.Service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@SecurityRequirement(name = "Authentication")
public class BookingRestController {

    private final BookingService bookingService;

    public BookingRestController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<List<Booking>> findAll() {
        return ResponseEntity.ok(this.bookingService.findAll());
    }

    @PostMapping("/add-booking")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Booking> addBooking(@RequestBody BookingDTO bookingDTO) throws Exception {
        return ResponseEntity.ok(this.bookingService.create(bookingDTO));
    }

    @PutMapping("/edit-booking/{ID}")
    @PreAuthorize("hasRole('DEVELOPER')")
    public ResponseEntity<Booking> editBooking(@PathVariable Long ID,
                                               @RequestBody BookingDTO bookingDTO) throws Exception {
        return ResponseEntity.ok(this.bookingService.update(ID, bookingDTO));
    }

    @DeleteMapping("delete-booking/{ID}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long ID) {
        this.bookingService.delete(ID);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("reservation/{ID}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<Booking> reservation(@PathVariable Long ID) throws Exception {
        return ResponseEntity.ok(this.bookingService.reservation(ID));
    }
}
