package mk.ukim.finki.eimt.lab.Model.DTO;

import mk.ukim.finki.eimt.lab.Model.Author;
import mk.ukim.finki.eimt.lab.Model.BookCategory;

public class BookDTO {

    private String name;

    private BookCategory category;

    private Long authorID;

    private int availableCopies;

    public BookDTO() {
    }

    public BookDTO(String name, BookCategory category, Long authorID, int availableCopies) {
        this.name = name;
        this.category = category;
        this.authorID = authorID;
        this.availableCopies = availableCopies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BookCategory getCategory() {
        return category;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
    }

    public Long getAuthorID() {
        return authorID;
    }

    public void setAuthorID(Long authorID) {
        this.authorID = authorID;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }
}
