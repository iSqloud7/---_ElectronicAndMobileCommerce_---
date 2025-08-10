package mk.ukim.finki.eimt.lab.Service.Application.Implementation;

import mk.ukim.finki.eimt.lab.Model.DTO.BookingDTO.CreateBookingDTO;
import mk.ukim.finki.eimt.lab.Model.DTO.BookingDTO.DisplayBookingDTO;
import mk.ukim.finki.eimt.lab.Model.Domain.Booking;
import mk.ukim.finki.eimt.lab.Model.Domain.Host;
import mk.ukim.finki.eimt.lab.Repository.HostRepository;
import mk.ukim.finki.eimt.lab.Service.Application.BookingApplicationService;
import mk.ukim.finki.eimt.lab.Service.Domain.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingApplicationServiceImpl implements BookingApplicationService {

    private final BookingService bookingService;
    private final HostRepository hostRepository;

    public BookingApplicationServiceImpl(BookingService bookingService, HostRepository hostRepository) {
        this.bookingService = bookingService;
        this.hostRepository = hostRepository;
    }

    @Override
    public List<Booking> findAll() {
        return this.bookingService.findAll();
    }

    @Override
    public DisplayBookingDTO create(CreateBookingDTO createBookingDTO) throws Exception {
        Host host_obj = this.hostRepository.findById(createBookingDTO.hostID()).orElseThrow(Exception::new);

        Booking booking = createBookingDTO.toBooking(host_obj);

        return Optional.of(this.bookingService.create(booking)).map(DisplayBookingDTO::from).get();
    }

    @Override
    public DisplayBookingDTO update(Long ID, CreateBookingDTO createBookingDTO) throws Exception {
        Host host_obj = this.hostRepository.findById(createBookingDTO.hostID()).orElseThrow(Exception::new);

        return Optional.of(this.bookingService.update(
                ID,
                createBookingDTO.toBooking(host_obj)
        )).map(DisplayBookingDTO::from).get();
    }

    @Override
    public void delete(Long ID) {
        this.bookingService.delete(ID);
    }

    @Override
    public Booking reservation(Long ID) throws Exception {
        return this.bookingService.reservation(ID);
    }
}