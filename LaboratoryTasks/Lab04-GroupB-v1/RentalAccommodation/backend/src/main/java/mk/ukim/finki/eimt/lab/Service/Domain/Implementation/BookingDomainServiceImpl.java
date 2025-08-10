package mk.ukim.finki.eimt.lab.Service.Domain.Implementation;

import mk.ukim.finki.eimt.lab.Model.Entities.Booking;
import mk.ukim.finki.eimt.lab.Model.Views.BookingsPerHostView;
import mk.ukim.finki.eimt.lab.Repository.BookingRepository;
import mk.ukim.finki.eimt.lab.Repository.BookingsPerHostViewRepository;
import mk.ukim.finki.eimt.lab.Repository.HostRepository;
import mk.ukim.finki.eimt.lab.Service.Domain.BookingDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingDomainServiceImpl implements BookingDomainService {

    private final BookingRepository bookingRepository;
    private final HostRepository hostRepository;
    private final BookingsPerHostViewRepository bookingsPerHostViewRepository;

    public BookingDomainServiceImpl(BookingRepository bookingRepository, HostRepository hostRepository, BookingsPerHostViewRepository bookingsPerHostViewRepository) {
        this.bookingRepository = bookingRepository;
        this.hostRepository = hostRepository;
        this.bookingsPerHostViewRepository = bookingsPerHostViewRepository;
    }

    @Override
    public List<Booking> findAll() {
        return this.bookingRepository.findAll();
    }

    @Override
    public Optional<Booking> create(Booking booking) throws Exception {
        return Optional.of(this.bookingRepository.save(booking));
    }

    @Override
    public Optional<Booking> update(Long ID, Booking booking) {
        Optional<Booking> booking_obj = this.bookingRepository.findById(ID);

        if (booking_obj.isPresent()) {
            Booking existing_booking = booking_obj.get();
            existing_booking.setName(booking.getName());
            existing_booking.setCategory(booking.getCategory());
            existing_booking.setHost(booking.getHost());
            existing_booking.setNumOfRooms(booking.getNumOfRooms());

            return Optional.of(this.bookingRepository.save(existing_booking));
        }

        return Optional.empty();
    }

    @Override
    public void delete(Long ID) {
        this.bookingRepository.deleteById(ID);
    }

    @Override
    public Optional<Booking> reservation(Long ID) throws Exception {
        Booking booking_obj = this.bookingRepository.findById(ID).orElseThrow(Exception::new);

        if (booking_obj.isBooked()) { // everything is booked
            return Optional.of(booking_obj);
        }

        if (booking_obj.getNumOfRooms() - 1 == 0) { // one more place left
            booking_obj.setNumOfRooms(booking_obj.getNumOfRooms() - 1);
            booking_obj.setBooked(true);

            return Optional.of(this.bookingRepository.save(booking_obj));
        } else { // free for booking
            booking_obj.setNumOfRooms(booking_obj.getNumOfRooms() - 1);

            return Optional.of(this.bookingRepository.save(booking_obj));
        }
    }

    @Override
    public void refreshMaterializedView() {
        this.bookingsPerHostViewRepository.refreshMaterializedView();
    }

    @Override
    public Optional<List<BookingsPerHostView>> findAllBookingsPerHostView() {
        return Optional.of(this.bookingsPerHostViewRepository.findAll());
    }
}
