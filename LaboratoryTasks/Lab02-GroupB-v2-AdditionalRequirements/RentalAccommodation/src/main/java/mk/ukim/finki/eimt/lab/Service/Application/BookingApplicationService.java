package mk.ukim.finki.eimt.lab.Service.Application;

import mk.ukim.finki.eimt.lab.Model.DTO.BookingDTO.CreateBookingDTO;
import mk.ukim.finki.eimt.lab.Model.DTO.BookingDTO.DisplayBookingDTO;
import mk.ukim.finki.eimt.lab.Model.Domain.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingApplicationService {

    List<Booking> findAll();

    DisplayBookingDTO create(CreateBookingDTO createBookingDTO) throws Exception;

    DisplayBookingDTO update(Long ID, CreateBookingDTO createBookingDTO) throws Exception;

    void delete(Long ID);

    Booking reservation(Long ID) throws Exception;
}
