package mk.ukim.finki.eimt.lab.Model.DTO.BookingDTO;

import mk.ukim.finki.eimt.lab.Model.Domain.Booking;
import mk.ukim.finki.eimt.lab.Model.Domain.Host;
import mk.ukim.finki.eimt.lab.Model.Enumerations.BookingCategory;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayBookingDTO(
        Long ID,
        String name,
        BookingCategory category,
        Long hostID,
        int numOfRooms
) {
    public static DisplayBookingDTO from(Booking booking) {
        return new DisplayBookingDTO(
                booking.getID(),
                booking.getName(),
                booking.getCategory(),
                booking.getHost().getID(),
                booking.getNumOfRooms()
        );
    }

    public static List<DisplayBookingDTO> from(List<Booking> bookings) {
        return bookings.stream()
                .map(DisplayBookingDTO::from)
                .collect(Collectors.toList());
    }

    public Booking toBooking(Host host) {
        return new Booking(ID, name, category, host, numOfRooms);
    }
}