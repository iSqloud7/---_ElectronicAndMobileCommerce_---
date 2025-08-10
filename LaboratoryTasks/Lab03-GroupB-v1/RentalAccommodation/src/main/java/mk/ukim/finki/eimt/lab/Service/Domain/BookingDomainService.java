package mk.ukim.finki.eimt.lab.Service.Domain;

import mk.ukim.finki.eimt.lab.Model.Domain.Booking;
import mk.ukim.finki.eimt.lab.Model.Views.BookingsPerHostView;

import java.util.List;
import java.util.Optional;

public interface BookingDomainService {

    List<Booking> findAll();

    Booking create(Booking booking) throws Exception;

    Booking update(Long ID, Booking booking) throws Exception;

    void delete(Long ID);

    Booking reservation(Long ID) throws Exception;

    void refreshMaterializedView();

    Optional<List<BookingsPerHostView>> findAllBookingsPerHostView();
}
