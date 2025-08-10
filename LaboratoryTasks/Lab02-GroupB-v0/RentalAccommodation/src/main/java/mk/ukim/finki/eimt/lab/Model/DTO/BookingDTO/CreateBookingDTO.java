package mk.ukim.finki.eimt.lab.Model.DTO.BookingDTO;

import mk.ukim.finki.eimt.lab.Model.Domain.Booking;
import mk.ukim.finki.eimt.lab.Model.Domain.Host;
import mk.ukim.finki.eimt.lab.Model.Enumerations.BookingCategory;

import java.util.List;
import java.util.stream.Collectors;

public record CreateBookingDTO(
        String name,
        BookingCategory category,
        Long hostID,
        int numOfRooms
) {
    public static CreateBookingDTO from(Booking booking) {
        return new CreateBookingDTO(
                booking.getName(),
                booking.getCategory(),
                booking.getHost().getID(),
                booking.getNumOfRooms()
        );
    }

    public static List<CreateBookingDTO> from(List<Booking> bookings) {
        return bookings.stream()
                .map(CreateBookingDTO::from)
                .collect(Collectors.toList());
    }

    public Booking toBooking(Host host) {
        return new Booking(name, category, host, numOfRooms);
    }
}
