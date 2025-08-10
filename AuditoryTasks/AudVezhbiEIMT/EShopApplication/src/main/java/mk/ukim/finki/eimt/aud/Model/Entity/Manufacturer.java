package mk.ukim.finki.eimt.aud.Model.Entity;

import jakarta.persistence.*;

// @Data
@Entity
@Table(name = "MANUFACTURERS")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name = "manufacturer_name")
    private String name;

    @Column(name = "manufacturer_address")
    private String address;

    public Manufacturer() {
    }

    public Manufacturer(String name, String address) {
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
