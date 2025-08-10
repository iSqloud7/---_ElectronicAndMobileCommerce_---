package mk.ukim.finki.eimt.lab.Model.Views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

//@Data
@Entity
@Immutable
@Subselect("SELECT * FROM public.books_per_author")
public class BooksPerAuthorView {

    @Id
    @Column(name = "author_id")
    private Long authorID;

    @Column(name = "number_of_books")
    private Integer numberOfBooks;

    public BooksPerAuthorView() {
    }

    public BooksPerAuthorView(Long authorID, Integer numberOfBooks) {
        this.authorID = authorID;
        this.numberOfBooks = numberOfBooks;
    }

    public Long getAuthorID() {
        return authorID;
    }


    public void setAuthorID(Long authorID) {
        this.authorID = authorID;
    }

    public Integer getNumberOfBooks() {
        return numberOfBooks;
    }

    public void setNumberOfBooks(Integer numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }
}
