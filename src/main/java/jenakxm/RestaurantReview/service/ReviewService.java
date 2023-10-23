package jenakxm.RestaurantReview.service;

import jenakxm.RestaurantReview.domain.Review;
import jenakxm.RestaurantReview.dto.AddReviewRequest;
import jenakxm.RestaurantReview.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    public Review save(AddReviewRequest request) {
        return reviewRepository.save(request.toEntity());
    }

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }
}
