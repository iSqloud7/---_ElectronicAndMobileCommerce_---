package mk.ukim.finki.eimt.lab.Service.Domain;

import mk.ukim.finki.eimt.lab.Model.DTO.ReservationListDTO.DisplayReservationListDTO;

public interface ReservationListService {

    DisplayReservationListDTO addBookingToReservationList(String username, Long bookingID) throws Exception;

    DisplayReservationListDTO clearReservationList(String username) throws Exception;
}