package mk.ukim.finki.eimt.lab.Service.Application.Implementation;

import mk.ukim.finki.eimt.lab.Model.DTO.BookingDTO.BookingDTO;
import mk.ukim.finki.eimt.lab.Model.DTO.BookingDTO.CreateBookingDTO;
import mk.ukim.finki.eimt.lab.Model.DTO.BookingDTO.DisplayBookingDTO;
import mk.ukim.finki.eimt.lab.Model.Domain.Booking;
import mk.ukim.finki.eimt.lab.Model.Domain.Host;
import mk.ukim.finki.eimt.lab.Repository.BookingRepository;
import mk.ukim.finki.eimt.lab.Repository.HostRepository;
import mk.ukim.finki.eimt.lab.Service.Application.BookingService;
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
    public DisplayBookingDTO create(CreateBookingDTO createBookingDTO) throws Exception {
        Host host_obj = this.hostRepository.findById(createBookingDTO.hostID()).orElseThrow(Exception::new);
        Booking booking_obj = new Booking(createBookingDTO.name(), createBookingDTO.category(), host_obj, createBookingDTO.numOfRooms());

        Booking booking = this.bookingRepository.save(createBookingDTO.toBooking(host_obj));
        return DisplayBookingDTO.from(booking);
    }

    @Override
    public DisplayBookingDTO update(Long ID, CreateBookingDTO createBookingDTO) throws Exception {
        Booking booking_obj = this.bookingRepository.findById(ID).orElseThrow(Exception::new);
        Host host_obj = this.hostRepository.findById(createBookingDTO.hostID()).orElseThrow(Exception::new);

        booking_obj.setName(createBookingDTO.name());
        booking_obj.setCategory(createBookingDTO.category());
        booking_obj.setHost(host_obj);
        booking_obj.setNumOfRooms(createBookingDTO.numOfRooms());

        Booking booking = this.bookingRepository.save(createBookingDTO.toBooking(host_obj));
        return DisplayBookingDTO.from(booking);
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
