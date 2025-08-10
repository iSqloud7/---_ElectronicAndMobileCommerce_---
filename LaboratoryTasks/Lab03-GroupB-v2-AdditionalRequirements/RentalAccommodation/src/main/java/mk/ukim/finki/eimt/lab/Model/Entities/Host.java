package mk.ukim.finki.eimt.lab.Model.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "HOSTS")
public class Host {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    // Host: (M) | Country: (1)
    @ManyToOne
    private Country country;

    public Host() {
    }

    public Host(String name, String surname, Country country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }

    public Host(Long ID, String name, String surname, Country country) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.country = country;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
