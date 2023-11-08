package jenakxm.RestaurantReview.controller;

import jenakxm.RestaurantReview.domain.Review;
import jenakxm.RestaurantReview.dto.AddReviewRequest;
import jenakxm.RestaurantReview.dto.ReviewResponse;
import jenakxm.RestaurantReview.dto.UpdateReviewRequest;
import jenakxm.RestaurantReview.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class ReviewApiController {
    private final ReviewService reviewService;

    @PostMapping("/api/reviews")
    public ResponseEntity<Review> addReview(@RequestBody AddReviewRequest request) {
        Review savedReview = reviewService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
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

    @PutMapping("/api/reviews/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable long id, @RequestBody UpdateReviewRequest request) {
        Review updatedReview = reviewService.update(id, request);

        return ResponseEntity.ok().body(updatedReview);
    }

    @GetMapping("/api/reviews/search")
    public ResponseEntity<List<ReviewResponse>> searchReviews(@RequestParam(name = "keyword") String keyword) {
        List<Review> searchResults = reviewService.searchByTitleAndContentContaining(keyword);

        List<ReviewResponse> reviews = searchResults.stream()
                .map(ReviewResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(reviews);
    }

    @GetMapping("/api/reviews")
    public List<Review> getReviews(@RequestParam(name = "sort", required = false, defaultValue = "desc") String sortOption) {
        List<Review> reviews;

        if ("oldest".equals(sortOption)) {
            // 오래된순 정렬
            reviews = reviewService.findAllByOrderByCreatedAtAsc();
        } else {
            // 최신순 정렬 (기본값)
            reviews = reviewService.findAllByOrderByCreatedAtDesc();
        }

        return reviews;
    }
}
