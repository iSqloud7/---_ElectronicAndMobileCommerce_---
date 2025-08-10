package mk.ukim.finki.eimt.lab.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "BOOKINGS")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name = "booking_name")
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "booking_category")
    private BookingCategory category;

    // Booking: (M) | Host: (1)
    @ManyToOne
    // @Column(name = "booking_host")
    private Host host;

    private int numRooms;

    private boolean booked;

    public Booking() {
    }

    public Booking(String name, BookingCategory category, Host host, int numRooms) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
        this.booked = false;
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

    public int getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(int numRooms) {
        this.numRooms = numRooms;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }
}
