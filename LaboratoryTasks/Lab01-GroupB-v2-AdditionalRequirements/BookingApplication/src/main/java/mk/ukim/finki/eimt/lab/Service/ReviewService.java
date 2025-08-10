package mk.ukim.finki.eimt.lab.Service;

import mk.ukim.finki.eimt.lab.DataTransferObject_DTO.ReviewDTO;
import mk.ukim.finki.eimt.lab.Model.Review;

import java.util.List;

public interface ReviewService {

    Review addReview(Long ID, ReviewDTO reviewDTO) throws Exception;

    List<ReviewDTO> getReviewByBooking(Long bookingID) throws Exception;

    double getAverageRating(Long bookingID) throws Exception;
}
