package mk.ukim.finki.eimt.lab.Service.Domain.Implementation;

import mk.ukim.finki.eimt.lab.Model.Domain.Booking;
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
    public Booking create(Booking booking) throws Exception {
        return this.bookingRepository.save(booking);
    }

    @Override
    public Booking update(Long ID, Booking booking) throws Exception {
        Booking booking_obj = this.bookingRepository.findById(ID).orElseThrow(Exception::new);
        // Host host_obj = this.hostRepository.findById(bookingDTO.getHostID()).orElseThrow(Exception::new);

        booking_obj.setName(booking.getName());
        booking_obj.setCategory(booking.getCategory());
        booking_obj.setHost(booking.getHost());
        booking_obj.setNumOfRooms(booking.getNumOfRooms());

        return this.bookingRepository.save(booking_obj);
    }

    @Override
    public void delete(Long ID) {
        this.bookingRepository.deleteById(ID);
    }

    @Override
    public Booking reservation(Long ID) throws Exception {
        Booking booking_obj = this.bookingRepository.findById(ID).orElseThrow(Exception::new);

        if (booking_obj.isBooked()) { // everything is booked
            return booking_obj;
        }

        if (booking_obj.getNumOfRooms() - 1 == 0) { // one more place left
            booking_obj.setNumOfRooms(booking_obj.getNumOfRooms() - 1);
            booking_obj.setBooked(true);

            return this.bookingRepository.save(booking_obj);
        } else { // free for booking
            booking_obj.setNumOfRooms(booking_obj.getNumOfRooms() - 1);

            return this.bookingRepository.save(booking_obj);
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
