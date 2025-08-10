package mk.ukim.finki.eimt.lab.Service;

import mk.ukim.finki.eimt.lab.DataTransferObject_DTO.BookingDTO;
import mk.ukim.finki.eimt.lab.Model.Booking;

import java.util.List;

public interface BookingService {

    List<Booking> findAllBookings();

    Booking create(BookingDTO bookingDTO) throws Exception;

    Booking update(Long ID, BookingDTO bookingDTO) throws Exception;

    void delete(Long ID);

    Booking reservation(Long ID) throws Exception;
}
