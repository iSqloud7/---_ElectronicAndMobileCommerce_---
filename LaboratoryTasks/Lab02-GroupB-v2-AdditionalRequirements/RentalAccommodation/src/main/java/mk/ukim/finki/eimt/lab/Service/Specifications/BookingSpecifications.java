package mk.ukim.finki.eimt.lab.Service.Specifications;

import jakarta.persistence.criteria.Predicate;
import mk.ukim.finki.eimt.lab.Model.Domain.Booking;
import mk.ukim.finki.eimt.lab.Model.Enumerations.BookingCategory;
import org.springframework.data.jpa.domain.Specification;

public class BookingSpecifications {

    public static Specification<Booking> filter(String name, BookingCategory category, Long hostId, Integer numOfRooms, Boolean booked) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (name != null && !name.isEmpty()) {
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }

            if (category != null) {
                predicate = cb.and(predicate, cb.equal(root.get("category"), category));
            }

            if (hostId != null) {
                predicate = cb.and(predicate, cb.equal(root.get("host").get("id"), hostId));
            }

            if (numOfRooms != null) {
                predicate = cb.and(predicate, cb.equal(root.get("numOfRooms"), numOfRooms));
            }

            if (booked != null) {
                predicate = cb.and(predicate, cb.equal(root.get("booked"), booked));
            }

            return predicate;
        };
    }
}