package mk.ukim.finki.eimt.lab.Model.Views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Entity
@Subselect("select * from public.bookings_per_host")
@Immutable
public class BookingsPerHostView {

    @Id
    @Column(name = "host_id")
    private Long hostID;

    @Column(name = "num_of_bookings")
    private Integer numOfBookings;

    public BookingsPerHostView() {
    }

    public BookingsPerHostView(Long hostID, Integer numOfBookings) {
        this.hostID = hostID;
        this.numOfBookings = numOfBookings;
    }

    public Long getHostID() {
        return hostID;
    }

    public void setHostID(Long hostID) {
        this.hostID = hostID;
    }

    public Integer getNumOfBookings() {
        return numOfBookings;
    }

    public void setNumOfBookings(Integer numOfBookings) {
        this.numOfBookings = numOfBookings;
    }
}
