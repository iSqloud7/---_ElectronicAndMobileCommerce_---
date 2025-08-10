package mk.ukim.finki.eimt.lab.Service.Application.Implementation;

import mk.ukim.finki.eimt.lab.Model.DTO.BookingDTO.CreateBookingDTO;
import mk.ukim.finki.eimt.lab.Model.DTO.BookingDTO.DisplayBookingDTO;
import mk.ukim.finki.eimt.lab.Model.Domain.Booking;
import mk.ukim.finki.eimt.lab.Model.Domain.Host;
import mk.ukim.finki.eimt.lab.Model.Views.BookingsPerHostView;
import mk.ukim.finki.eimt.lab.Repository.HostRepository;
import mk.ukim.finki.eimt.lab.Service.Application.BookingApplicationService;
import mk.ukim.finki.eimt.lab.Service.Domain.BookingDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingApplicationServiceImpl implements BookingApplicationService {

    private final BookingDomainService bookingDomainService;
    private final HostRepository hostDomainRepository;

    public BookingApplicationServiceImpl(BookingDomainService bookingService, HostRepository hostRepository) {
        this.bookingDomainService = bookingService;
        this.hostDomainRepository = hostRepository;
    }

    @Override
    public List<Booking> findAll() {
        return this.bookingDomainService.findAll();
    }

    @Override
    public DisplayBookingDTO create(CreateBookingDTO createBookingDTO) throws Exception {
        Host host_obj = this.hostDomainRepository.findById(createBookingDTO.hostID()).orElseThrow(Exception::new);

        Booking booking = createBookingDTO.toBooking(host_obj);

        return Optional.of(this.bookingDomainService.create(booking)).map(DisplayBookingDTO::from).get();
    }

    @Override
    public DisplayBookingDTO update(Long ID, CreateBookingDTO createBookingDTO) throws Exception {
        Host host_obj = this.hostDomainRepository.findById(createBookingDTO.hostID()).orElseThrow(Exception::new);

        return Optional.of(this.bookingDomainService.update(
                ID,
                createBookingDTO.toBooking(host_obj)
        )).map(DisplayBookingDTO::from).get();
    }

    @Override
    public void delete(Long ID) {
        this.bookingDomainService.delete(ID);
    }

    @Override
    public Booking reservation(Long ID) throws Exception {
        return this.bookingDomainService.reservation(ID);
    }

    @Override
    public Optional<List<BookingsPerHostView>> findAllBookingsPerHostView() {
        return this.bookingDomainService.findAllBookingsPerHostView();
    }
}