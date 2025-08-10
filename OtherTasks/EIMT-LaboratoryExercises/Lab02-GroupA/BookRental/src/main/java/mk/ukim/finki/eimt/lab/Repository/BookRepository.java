package mk.ukim.finki.eimt.lab.Repository;

import mk.ukim.finki.eimt.lab.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
