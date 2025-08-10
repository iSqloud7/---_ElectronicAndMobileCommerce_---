package mk.ukim.finki.eimt.lab.Service.Domain;

import mk.ukim.finki.eimt.lab.Model.Domain.Booking;
import mk.ukim.finki.eimt.lab.Model.DTO.BookingDTO.BookingDTO;
import java.util.List;
import java.util.Optional;

public interface BookingService {

    List<Booking> findAll();

    Booking create(Booking booking) throws Exception;

    Booking update(Long ID, Booking booking) throws Exception;

    void delete(Long ID);

    Booking reservation(Long ID) throws Exception;
}
