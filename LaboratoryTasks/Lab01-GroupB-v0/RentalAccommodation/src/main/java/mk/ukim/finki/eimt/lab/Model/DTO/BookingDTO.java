package mk.ukim.finki.eimt.lab.Model.DTO;

import mk.ukim.finki.eimt.lab.Model.BookingCategory;

public class BookingDTO {

    private String name;

    private BookingCategory category;

    private Long hostID;

    private int numOfRooms;

    public BookingDTO() {
    }

    public BookingDTO(String name, BookingCategory category, Long hostID, int numOfRooms) {
        this.name = name;
        this.category = category;
        this.hostID = hostID;
        this.numOfRooms = numOfRooms;
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

    public Long getHostID() {
        return hostID;
    }

    public void setHostID(Long hostID) {
        this.hostID = hostID;
    }

    public int getNumOfRooms() {
        return numOfRooms;
    }

    public void setNumOfRooms(int numOfRooms) {
        this.numOfRooms = numOfRooms;
    }
}
