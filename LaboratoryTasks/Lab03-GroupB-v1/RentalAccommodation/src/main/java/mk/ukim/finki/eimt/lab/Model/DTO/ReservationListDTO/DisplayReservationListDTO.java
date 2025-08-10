package mk.ukim.finki.eimt.lab.Model.DTO.ReservationListDTO;

import mk.ukim.finki.eimt.lab.Model.Domain.Booking;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public record DisplayReservationListDTO(
        List<String> bookingNames
) {
    public static DisplayReservationListDTO toDisplayReservationListDTO(Set<Booking> bookings) {
        List<String> bookingNames = new ArrayList<>();

        for (Booking booking : bookings) {
            bookingNames.add(booking.getName());
        }

        return new DisplayReservationListDTO(bookingNames);
    }
}