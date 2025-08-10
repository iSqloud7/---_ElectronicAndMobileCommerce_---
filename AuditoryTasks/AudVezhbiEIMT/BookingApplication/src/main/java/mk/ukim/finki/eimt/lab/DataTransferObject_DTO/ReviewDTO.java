package mk.ukim.finki.eimt.lab.DataTransferObject_DTO;

public class ReviewDTO {

    private int rating;

    private String description;

    public ReviewDTO() {
    }

    public ReviewDTO(int rating, String description) {
        this.rating = rating;
        this.description = description;
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
}
