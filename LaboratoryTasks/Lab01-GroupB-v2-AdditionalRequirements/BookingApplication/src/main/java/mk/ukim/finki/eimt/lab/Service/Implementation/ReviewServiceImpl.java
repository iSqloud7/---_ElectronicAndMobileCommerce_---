package mk.ukim.finki.eimt.lab.Service.Implementation;

import mk.ukim.finki.eimt.lab.DataTransferObject_DTO.ReviewDTO;
import mk.ukim.finki.eimt.lab.Model.Booking;
import mk.ukim.finki.eimt.lab.Model.Review;
import mk.ukim.finki.eimt.lab.Repository.BookingRepository;
import mk.ukim.finki.eimt.lab.Repository.ReviewRepository;
import mk.ukim.finki.eimt.lab.Service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final BookingRepository bookingRepository;
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(BookingRepository bookingRepository, ReviewRepository reviewRepository) {
        this.bookingRepository = bookingRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review addReview(Long ID, ReviewDTO reviewDTO) throws Exception {
        Booking booking_obj = this.bookingRepository.findById(ID).orElseThrow(Exception::new);

        Review review_obj = new Review(reviewDTO.getRating(), reviewDTO.getDescription(), booking_obj);

        return this.reviewRepository.save(review_obj);
    }

    @Override
    public List<ReviewDTO> getReviewByBooking(Long bookingID) throws Exception {
        Booking booking_obj = this.bookingRepository.findById(bookingID).orElseThrow(Exception::new);

        List<Review> reviews = reviewRepository.findByBookingID(bookingID);

        return reviews.stream()
                .map(review -> new ReviewDTO(review.getRating(), review.getDescription()))
                .collect(Collectors.toList());
    }

    @Override
    public double getAverageRating(Long bookingID) throws Exception {
        Booking booking_obj = this.bookingRepository.findById(bookingID).orElseThrow(Exception::new);

        List<Review> reviews = this.reviewRepository.findByBookingID(booking_obj.getID());

        return reviews.stream()
                .mapToInt(r -> r.getRating())
                .average()
                .orElse(0.0);
    }
}
