package mk.ukim.finki.eimt.aud.Repository;

import mk.ukim.finki.eimt.aud.Model.Entity.ShoppingCart;
import mk.ukim.finki.eimt.aud.Model.Entity.User;
import mk.ukim.finki.eimt.aud.Model.Enumerations.ShoppingCartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus status);
}
