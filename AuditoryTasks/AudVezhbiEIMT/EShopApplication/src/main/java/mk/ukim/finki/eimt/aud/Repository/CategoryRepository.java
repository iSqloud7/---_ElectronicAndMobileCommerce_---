package mk.ukim.finki.eimt.aud.Repository;

import mk.ukim.finki.eimt.aud.Model.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findAllByNameLike(String text);

    void deleteByName(String name);
}
