package mk.ukim.finki.eimt.lab.Repository;

import mk.ukim.finki.eimt.lab.Model.Entities.ReservationList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationListRepository extends JpaRepository<ReservationList, Long> {

}