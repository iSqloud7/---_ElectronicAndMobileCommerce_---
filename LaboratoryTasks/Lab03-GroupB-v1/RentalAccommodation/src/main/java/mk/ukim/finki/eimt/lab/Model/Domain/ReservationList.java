package mk.ukim.finki.eimt.lab.Model.Domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
