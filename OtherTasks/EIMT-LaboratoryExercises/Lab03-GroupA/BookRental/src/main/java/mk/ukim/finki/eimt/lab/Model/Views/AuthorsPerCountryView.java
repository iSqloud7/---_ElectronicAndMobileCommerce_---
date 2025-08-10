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
@Subselect("SELECT * FROM public.authors_per_country")
public class AuthorsPerCountryView {

    @Id
    @Column(name = "country_id")
    private Long country_id;

    @Column(name = "number_of_authors")
    private Integer numberOfAuthors;

    public AuthorsPerCountryView() {
    }

    public AuthorsPerCountryView(Long country_id, Integer numberOfAuthors) {
        this.country_id = country_id;
        this.numberOfAuthors = numberOfAuthors;
    }

    public Long getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Long country_id) {
        this.country_id = country_id;
    }

    public Integer getNumberOfAuthors() {
        return numberOfAuthors;
    }

    public void setNumberOfAuthors(Integer numberOfAuthors) {
        this.numberOfAuthors = numberOfAuthors;
    }
}
