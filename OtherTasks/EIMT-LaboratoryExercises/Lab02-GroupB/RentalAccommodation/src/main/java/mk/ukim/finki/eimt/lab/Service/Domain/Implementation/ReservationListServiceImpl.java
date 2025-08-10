package mk.ukim.finki.eimt.lab.Service.Domain.Implementation;

import mk.ukim.finki.eimt.lab.Model.Domain.Booking;
import mk.ukim.finki.eimt.lab.Model.DTO.ReservationListDTO.DisplayReservationListDTO;
import mk.ukim.finki.eimt.lab.Model.Domain.ReservationList;
import mk.ukim.finki.eimt.lab.Model.Domain.User;
import mk.ukim.finki.eimt.lab.Repository.BookingRepository;
import mk.ukim.finki.eimt.lab.Repository.ReservationListRepository;
import mk.ukim.finki.eimt.lab.Repository.UserRepository;
import mk.ukim.finki.eimt.lab.Service.Domain.BookingService;
import mk.ukim.finki.eimt.lab.Service.Domain.ReservationListService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationListServiceImpl implements ReservationListService {

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final ReservationListRepository reservationListRepository;
    private final BookingService bookingService;

    public ReservationListServiceImpl(UserRepository userRepository, ReservationListRepository reservationListRepository, BookingRepository bookingRepository, ReservationListRepository reservationListRepository1, BookingService bookingService) {
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.reservationListRepository = reservationListRepository1;
        this.bookingService = bookingService;
    }

    public DisplayReservationListDTO addBookingToReservationList(String username, Long bookingID) throws Exception {
        User user = this.userRepository.findByUsername(username).orElseThrow(Exception::new);
        Booking booking = this.bookingRepository.findById(bookingID).orElseThrow(Exception::new);

        if (!booking.isBooked()) {
            ReservationList reservationList = user.getReservationList();
            reservationList.getBookings().add(booking);

            this.reservationListRepository.save(reservationList);

            return DisplayReservationListDTO.toDisplayReservationListDTO(reservationList.getBookings());
        } else {
            throw new Exception();
        }
    }

    public DisplayReservationListDTO clearReservationList(String username) throws Exception {
        User user = this.userRepository.findByUsername(username).orElseThrow(Exception::new);
        ReservationList reservationList = user.getReservationList();
        List<Booking> bookings = reservationList.getBookings();

        for (Booking booking : bookings) {
            this.bookingService.reservation(booking.getID());
        }

        reservationList.getBookings().clear();

        this.reservationListRepository.save(reservationList);

        return DisplayReservationListDTO.toDisplayReservationListDTO(bookings);
    }
}