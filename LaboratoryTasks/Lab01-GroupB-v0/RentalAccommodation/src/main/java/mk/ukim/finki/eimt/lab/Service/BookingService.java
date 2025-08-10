package mk.ukim.finki.eimt.lab.Service;

import mk.ukim.finki.eimt.lab.Model.Booking;
import mk.ukim.finki.eimt.lab.Model.DTO.BookingDTO;
import java.util.List;

public interface BookingService {

    List<Booking> findAll();

    Booking create(BookingDTO bookingDTO) throws Exception;

    Booking update(Long ID, BookingDTO bookingDTO) throws Exception;

    void delete(Long ID);

    Booking reservation(Long ID) throws Exception;
}
