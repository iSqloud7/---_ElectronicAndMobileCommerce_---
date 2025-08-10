package mk.ukim.finki.eimt.lab.Model.Domain;

import jakarta.persistence.*;
import mk.ukim.finki.eimt.lab.Model.Enumerations.UserRole;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    private String username;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

    @OneToOne
    private ReservationList reservationList;

    public User() {
    }

    public User(String username, String password, UserRole userRole) {
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }

    public User(String username, String password, UserRole userRole, ReservationList reservationList) {
        this.username = username;
        this.password = password;
        this.userRole = userRole;
        this.reservationList = reservationList;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public ReservationList getReservationList() {
        return reservationList;
    }

    public void setReservationList(ReservationList reservationList) {
        this.reservationList = reservationList;
    }
}
