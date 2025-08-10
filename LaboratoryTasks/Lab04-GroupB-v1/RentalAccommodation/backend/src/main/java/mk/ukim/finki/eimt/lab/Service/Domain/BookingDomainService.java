package mk.ukim.finki.eimt.lab.Service.Domain;

import mk.ukim.finki.eimt.lab.Model.Entities.Booking;
import mk.ukim.finki.eimt.lab.Model.Views.BookingsPerHostView;

import java.util.List;
import java.util.Optional;

public interface BookingDomainService {

    List<Booking> findAll();

    Optional<Booking> create(Booking booking) throws Exception;

    Optional<Booking> update(Long ID, Booking booking);

    void delete(Long ID);

    Optional<Booking> reservation(Long ID) throws Exception;

    void refreshMaterializedView();

    Optional<List<BookingsPerHostView>> findAllBookingsPerHostView();
}
