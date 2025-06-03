package mk.ukim.finki.fooddeliverybackend.repository;

import mk.ukim.finki.fooddeliverybackend.model.domain.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
}
