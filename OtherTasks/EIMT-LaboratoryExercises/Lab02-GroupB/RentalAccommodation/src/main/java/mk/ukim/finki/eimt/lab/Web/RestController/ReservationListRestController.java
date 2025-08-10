package mk.ukim.finki.eimt.lab.Web.RestController;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.eimt.lab.Model.DTO.ReservationListDTO.AddBookingToReservationListDTO;
import mk.ukim.finki.eimt.lab.Model.DTO.ReservationListDTO.DisplayReservationListDTO;
import mk.ukim.finki.eimt.lab.Model.DTO.UserDTO.DisplayUserDTO;
import mk.ukim.finki.eimt.lab.Service.Domain.ReservationListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservations")
public class ReservationListRestController {

    private final ReservationListService reservationListService;

    public ReservationListRestController(ReservationListService reservationListService) {
        this.reservationListService = reservationListService;
    }

    @PostMapping("/add-booking")
    public ResponseEntity<DisplayReservationListDTO> addBookingToReservationList(@RequestBody AddBookingToReservationListDTO addBookingToReservationListDTO,
                                                                                 HttpServletRequest httpServletRequest) throws Exception {
        DisplayUserDTO user = (DisplayUserDTO) httpServletRequest.getSession().getAttribute("user");

        return ResponseEntity.ok(this.reservationListService.addBookingToReservationList(user.username(), addBookingToReservationListDTO.bookingID()));
    }

    @PostMapping("/clear-booking")
    public ResponseEntity<DisplayReservationListDTO> clearReservationList(HttpServletRequest httpServletRequest) throws Exception {
        DisplayUserDTO user = (DisplayUserDTO) httpServletRequest.getSession().getAttribute("user");

        return ResponseEntity.ok(this.reservationListService.clearReservationList(user.username()));
    }
}