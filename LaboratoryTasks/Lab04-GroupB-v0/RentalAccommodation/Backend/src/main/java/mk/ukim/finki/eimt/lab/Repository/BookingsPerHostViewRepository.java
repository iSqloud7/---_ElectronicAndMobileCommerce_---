package mk.ukim.finki.eimt.lab.Repository;

import jakarta.transaction.Transactional;
import mk.ukim.finki.eimt.lab.Model.Views.BookingsPerHostView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingsPerHostViewRepository extends JpaRepository<BookingsPerHostView, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW public.bookings_per_host", nativeQuery = true)
    void refreshMaterializedView();
}
