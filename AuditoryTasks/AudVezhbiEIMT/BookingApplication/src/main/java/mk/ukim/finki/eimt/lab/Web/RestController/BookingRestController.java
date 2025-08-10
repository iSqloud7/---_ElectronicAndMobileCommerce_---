package mk.ukim.finki.eimt.lab.Web.RestController;

import mk.ukim.finki.eimt.lab.DataTransferObject_DTO.BookingDTO;
import mk.ukim.finki.eimt.lab.Model.Booking;
import mk.ukim.finki.eimt.lab.Service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingRestController {

    private final BookingService bookingService;

    public BookingRestController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<List<Booking>> findAll() {
        return ResponseEntity.ok(this.bookingService.findAllBookings());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add-booking")
    public ResponseEntity<Booking> addBooking(@RequestBody BookingDTO bookingDTO) throws Exception {
        return ResponseEntity.ok(this.bookingService.create(bookingDTO));
    }

    @PreAuthorize("hasRole('DEVELOPER')")
    @PutMapping("/edit-booking/{ID}")
    public ResponseEntity<Booking> update(@PathVariable Long ID,
                                          @RequestBody BookingDTO bookingDTO) throws Exception {
        return ResponseEntity.ok(this.bookingService.update(ID, bookingDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete-booking/{ID}")
    public ResponseEntity<Void> delete(@PathVariable Long ID) {
        this.bookingService.delete(ID);

        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping("/reservation/{ID}")
    public ResponseEntity<Booking> reservation(@PathVariable Long ID) throws Exception {
        return ResponseEntity.ok(this.bookingService.reservation(ID));
    }
}
