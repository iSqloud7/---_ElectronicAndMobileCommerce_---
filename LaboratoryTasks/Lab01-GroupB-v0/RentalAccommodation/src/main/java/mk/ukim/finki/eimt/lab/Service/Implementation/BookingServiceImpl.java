package mk.ukim.finki.eimt.lab.Service.Implementation;

import mk.ukim.finki.eimt.lab.Model.Booking;
import mk.ukim.finki.eimt.lab.Model.DTO.BookingDTO;
import mk.ukim.finki.eimt.lab.Model.Host;
import mk.ukim.finki.eimt.lab.Repository.BookingRepository;
import mk.ukim.finki.eimt.lab.Repository.HostRepository;
import mk.ukim.finki.eimt.lab.Service.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final HostRepository hostRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, HostRepository hostRepository) {
        this.bookingRepository = bookingRepository;
        this.hostRepository = hostRepository;
    }

    @Override
    public List<Booking> findAll() {
        return this.bookingRepository.findAll();
    }

    @Override
    public Booking create(BookingDTO bookingDTO) throws Exception {
        Host host_obj = this.hostRepository.findById(bookingDTO.getHostID()).orElseThrow(Exception::new);
        Booking booking_obj = new Booking(bookingDTO.getName(), bookingDTO.getCategory(), host_obj, bookingDTO.getNumOfRooms());

        return this.bookingRepository.save(booking_obj);
    }

    @Override
    public Booking update(Long ID, BookingDTO bookingDTO) throws Exception {
        Booking booking_obj = this.bookingRepository.findById(ID).orElseThrow(Exception::new);
        Host host_obj = this.hostRepository.findById(bookingDTO.getHostID()).orElseThrow(Exception::new);

        booking_obj.setName(bookingDTO.getName());
        booking_obj.setCategory(bookingDTO.getCategory());
        booking_obj.setHost(host_obj);
        booking_obj.setNumOfRooms(bookingDTO.getNumOfRooms());

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
}
