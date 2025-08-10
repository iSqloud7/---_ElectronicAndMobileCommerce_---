package mk.ukim.finki.eimt.lab.Model.Domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "RESERVATIONS")
public class ReservationList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    // ReservationList: (M) | Booking: (N)
    @ManyToMany
    private List<Booking> bookings = new ArrayList<>();

    public ReservationList() {
    }

    public ReservationList(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
