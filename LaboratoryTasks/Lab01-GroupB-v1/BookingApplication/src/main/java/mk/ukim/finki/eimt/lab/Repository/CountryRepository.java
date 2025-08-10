package mk.ukim.finki.eimt.lab.Repository;

import mk.ukim.finki.eimt.lab.Model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
