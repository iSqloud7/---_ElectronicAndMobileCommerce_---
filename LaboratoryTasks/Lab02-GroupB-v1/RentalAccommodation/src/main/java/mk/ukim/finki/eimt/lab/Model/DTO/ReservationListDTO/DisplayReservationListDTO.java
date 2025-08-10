package mk.ukim.finki.eimt.lab.Model.DTO.ReservationListDTO;

import mk.ukim.finki.eimt.lab.Model.Domain.Booking;

import java.util.ArrayList;
import java.util.List;

public record DisplayReservationListDTO(
        List<String> bookingNames
) {
    public static DisplayReservationListDTO toDisplayReservationListDTO(List<Booking> bookings) {
        List<String> bookingNames = new ArrayList<>();

        for (Booking booking : bookings) {
            bookingNames.add(booking.getName());
        }

        return new DisplayReservationListDTO(bookingNames);
    }
}