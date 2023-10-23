package jenakxm.RestaurantReview.controller;

import jenakxm.RestaurantReview.domain.Review;
import jenakxm.RestaurantReview.dto.AddReviewRequest;
import jenakxm.RestaurantReview.dto.ReviewResponse;
import jenakxm.RestaurantReview.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ReviewApiController {
    private final ReviewService reviewService;

    @PostMapping("/api/reviews")
    public ResponseEntity<Review> addReview(@RequestBody AddReviewRequest request) {
        Review savedReview = reviewService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
    }

    @GetMapping("/api/reviews")
    public ResponseEntity<List<ReviewResponse>> findAllReviews() {
        List<ReviewResponse> reviews = reviewService.findAll()
                .stream()
                .map(ReviewResponse::new)
                .toList();

        return ResponseEntity.ok().body(reviews);
    }

    @GetMapping("/api/reviews/{id}")
    public ResponseEntity<ReviewResponse> findReview(@PathVariable long id) {
        Review review = reviewService.findById(id);

        return ResponseEntity.ok().body(new ReviewResponse(review));
    }

    @DeleteMapping("/api/reviews/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable long id) {
        reviewService.delete(id);

        return ResponseEntity.ok().build();
    }
}
