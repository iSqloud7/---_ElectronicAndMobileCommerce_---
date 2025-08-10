package mk.ukim.finki.eimt.lab.Model;

import jakarta.persistence.*;

import java.util.Optional;

@Entity
@Table(name = "REVIEWS")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name = "review-rating")
    private int rating;

    @Column(name = "review-description")
    private String description;

    // Review: (M) | Booking: (1)
    @ManyToOne
    private Booking booking;

    public Review() {
    }

    public Review(int rating, String description, Booking booking) {
        this.rating = rating;
        this.description = description;
        this.booking = booking;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
