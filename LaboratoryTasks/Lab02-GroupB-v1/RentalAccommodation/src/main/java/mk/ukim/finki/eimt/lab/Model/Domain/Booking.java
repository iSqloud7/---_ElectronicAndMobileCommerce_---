package mk.ukim.finki.eimt.lab.Model.Domain;

import jakarta.persistence.*;
import mk.ukim.finki.eimt.lab.Model.Enumerations.BookingCategory;

@Entity
@Table(name = "BOOKINGS")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    private String name;

    @Enumerated(EnumType.STRING)
    private BookingCategory category;

    // Booking: (M) | Host: (1)
    @ManyToOne
    private Host host;

    private int numOfRooms;

    private boolean booked;

    public Booking() {

    }

    public Booking(String name, BookingCategory category, Host host, int numOfRooms) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numOfRooms = numOfRooms;
        this.booked = false;
    }

    public Booking(Long ID, String name, BookingCategory category, Host host, int numOfRooms) {
        this.ID = ID;
        this.name = name;
        this.category = category;
        this.host = host;
        this.numOfRooms = numOfRooms;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BookingCategory getCategory() {
        return category;
    }

    public void setCategory(BookingCategory category) {
        this.category = category;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public int getNumOfRooms() {
        return numOfRooms;
    }

    public void setNumOfRooms(int numOfRooms) {
        this.numOfRooms = numOfRooms;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }
}
