package mk.ukim.finki.eimt.lab.Model.Entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "RESERVATIONS")
public class ReservationList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    // ReservationList: (M) | Booking: (N)
    @ManyToMany
    private Set<Booking> bookings = new HashSet<>();

    public ReservationList() {
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }
}
