package mk.ukim.finki.eimt.lab.Web.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import mk.ukim.finki.eimt.lab.Model.DTO.BookingDTO.CreateBookingDTO;
import mk.ukim.finki.eimt.lab.Model.DTO.BookingDTO.DisplayBookingDTO;
import mk.ukim.finki.eimt.lab.Model.Domain.Booking;
import mk.ukim.finki.eimt.lab.Model.Views.BookingsPerHostView;
import mk.ukim.finki.eimt.lab.Repository.BookingsPerHostViewRepository;
import mk.ukim.finki.eimt.lab.Service.Application.BookingApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
@SecurityRequirement(name = "Authentication")
public class BookingRestController {

    private final BookingApplicationService bookingApplicationService;

    public BookingRestController(BookingApplicationService bookingApplicationService) {
        this.bookingApplicationService = bookingApplicationService;
    }

    @GetMapping
    public ResponseEntity<List<Booking>> findAll() {
        return ResponseEntity.ok(this.bookingApplicationService.findAll());
    }

    @PostMapping("/add-booking")
    @PreAuthorize("hasRole('HOST')")
    public ResponseEntity<DisplayBookingDTO> addBooking(@RequestBody CreateBookingDTO createBookingDTO) throws Exception {
        return ResponseEntity.ok(this.bookingApplicationService.create(createBookingDTO));
    }

    @PutMapping("/edit-booking/{ID}")
    @PreAuthorize("hasRole('HOST')")
    public ResponseEntity<DisplayBookingDTO> editBooking(@PathVariable Long ID,
                                                         @RequestBody CreateBookingDTO createBookingDTO) throws Exception {
        return ResponseEntity.ok(this.bookingApplicationService.update(ID, createBookingDTO));
    }

    @DeleteMapping("delete-booking/{ID}")
    @PreAuthorize("hasRole('HOST')")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long ID) {
        this.bookingApplicationService.delete(ID);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("reservation/{ID}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Booking> reservation(@PathVariable Long ID) throws Exception {
        return ResponseEntity.ok(this.bookingApplicationService.reservation(ID));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/by-host")
    public Optional<ResponseEntity<List<BookingsPerHostView>>> findAllBookingsPerHostView() {
        return Optional.of(this.bookingApplicationService.findAllBookingsPerHostView()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()));
    }
}
