package mk.ukim.finki.fooddeliverybackend.repository;

import mk.ukim.finki.fooddeliverybackend.model.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
