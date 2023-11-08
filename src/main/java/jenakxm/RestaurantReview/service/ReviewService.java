package jenakxm.RestaurantReview.service;

import jenakxm.RestaurantReview.domain.Review;
import jenakxm.RestaurantReview.dto.AddReviewRequest;
import jenakxm.RestaurantReview.dto.UpdateReviewRequest;
import jenakxm.RestaurantReview.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final List<Review> reviews; // 리뷰 목록 (예시 데이터)

    public Review save(AddReviewRequest request) {
        return reviewRepository.save(request.toEntity());
    }

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Review findById(long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    public void delete(long id) {
        reviewRepository.deleteById(id);
    }

    @Transactional
    public Review update(long id, UpdateReviewRequest request) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found:" + id));

        review.update(request.getRestaurant(), request.getTitle(), request.getContent());
        return review;
    }

    public List<Review> searchByTitleAndContentContaining(String keyword) {
        return reviewRepository.findByTitleContainingOrContentContaining(keyword, keyword);
    }

    public List<Review> findAllByOrderByCreatedAtAsc() {
        return reviewRepository.findAllByOrderByCreatedAtAsc();
    }

    public List<Review> findAllByOrderByCreatedAtDesc() {
        return reviewRepository.findAllByOrderByCreatedAtDesc();
    }
}
