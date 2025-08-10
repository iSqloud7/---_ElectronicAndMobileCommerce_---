package mk.ukim.finki.eimt.lab.Service.Implementation;

import mk.ukim.finki.eimt.lab.DataTransferObject_DTO.BookingDTO;
import mk.ukim.finki.eimt.lab.Model.Booking;
import mk.ukim.finki.eimt.lab.Model.Host;
import mk.ukim.finki.eimt.lab.Repository.BookingRepository;
import mk.ukim.finki.eimt.lab.Repository.HostRepository;
import mk.ukim.finki.eimt.lab.Service.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final HostRepository hostRepository;
    private final BookingRepository bookingRepository;

    public BookingServiceImpl(HostRepository hostRepository, BookingRepository bookingRepository) {
        this.hostRepository = hostRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<Booking> findAllBookings() {
        return this.bookingRepository.findAll();
    }

    @Override
    public Booking create(BookingDTO bookingDTO) throws Exception {
        Host host_obj = this.hostRepository.findById(bookingDTO.getHostID()).orElseThrow(Exception::new);
        Booking booking_obj = new Booking(bookingDTO.getName(), bookingDTO.getCategory(), host_obj, bookingDTO.getNumRooms());

        return this.bookingRepository.save(booking_obj);
    }

    @Override
    public Booking update(Long ID, BookingDTO bookingDTO) throws Exception {
        Booking booking_obj = this.bookingRepository.findById(ID).orElseThrow(Exception::new);
        Host host_obj = this.hostRepository.findById(bookingDTO.getHostID()).orElseThrow(Exception::new);

        booking_obj.setName(bookingDTO.getName());
        booking_obj.setCategory(bookingDTO.getCategory());
        booking_obj.setHost(host_obj);
        booking_obj.setNumRooms(bookingDTO.getNumRooms());

        return this.bookingRepository.save(booking_obj);
    }

    @Override
    public void delete(Long ID) {
        this.bookingRepository.deleteById(ID);
    }

    @Override
    public Booking reservation(Long ID) throws Exception {
        Booking booking_obj = this.bookingRepository.findById(ID).orElseThrow(Exception::new);

        if (booking_obj.isBooked()) {
            return booking_obj; // everything is booked
        }

        if (booking_obj.getNumRooms() <= 0) { // no more place left
            throw new Exception("No available rooms for booking ID: " + ID);
        }

        if (booking_obj.getNumRooms() - 1 == 0) { // one more place left
            booking_obj.setNumRooms(booking_obj.getNumRooms() - 1);
            booking_obj.setBooked(true);

            return this.bookingRepository.save(booking_obj);
        } else {
            booking_obj.setNumRooms(booking_obj.getNumRooms() - 1);

            return this.bookingRepository.save(booking_obj);
        }
    }
}
