package mk.ukim.finki.eimt.aud.Repository;

import mk.ukim.finki.eimt.aud.Model.Entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

}
