package mk.ukim.finki.eimt.lab.DataTransferObject_DTO;

import mk.ukim.finki.eimt.lab.Model.BookingCategory;

public class BookingDTO {

    private String name;

    private BookingCategory category;

    private Long hostID;

    private int numRooms;

    public BookingDTO() {
    }

    public BookingDTO(String name, BookingCategory category, Long hostID, int numRooms) {
        this.name = name;
        this.category = category;
        this.hostID = hostID;
        this.numRooms = numRooms;
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

    public int getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(int numRooms) {
        this.numRooms = numRooms;
    }
}
