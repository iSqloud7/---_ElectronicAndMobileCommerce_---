package mk.ukim.finki.eimt.lab.Web.RestController;

import mk.ukim.finki.eimt.lab.DataTransferObject_DTO.ReviewDTO;
import mk.ukim.finki.eimt.lab.Model.Review;
import mk.ukim.finki.eimt.lab.Service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/add-review/{ID}")
    public ResponseEntity<Review> addReview(@PathVariable Long ID,
                                            @RequestBody ReviewDTO reviewDTO) throws Exception {
        return ResponseEntity.ok(this.reviewService.addReview(ID, reviewDTO));
    }

    @GetMapping("/{ID}")
    public ResponseEntity<List<ReviewDTO>> getReviews(@PathVariable Long ID) throws Exception {
        return ResponseEntity.ok(this.reviewService.getReviewByBooking(ID));
    }

    @GetMapping("/{ID}/rating/average")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long ID) throws Exception {
        return ResponseEntity.ok(this.reviewService.getAverageRating(ID));
    }
}
