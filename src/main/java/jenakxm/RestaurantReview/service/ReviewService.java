package jenakxm.RestaurantReview.service;

import jenakxm.RestaurantReview.domain.Review;
import jenakxm.RestaurantReview.dto.AddReviewRequest;
import jenakxm.RestaurantReview.dto.UpdateReviewRequest;
import jenakxm.RestaurantReview.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
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

//    public List<Review> searchByTitle(String title) {
//        // title을 사용하여 리뷰를 검색하는 로직을 구현
//        // 예를 들어, 리뷰 저장소에서 제목에 일치하는 리뷰를 찾아 반환할 수 있습니다.
//        return reviewRepository.findByTitle(title);
//    }
//
//    // 내용으로 리뷰 검색
//    public List<Review> searchByContent(String content) {
//        // content를 사용하여 리뷰를 검색하는 로직을 구현
//        // 예를 들어, 리뷰 저장소에서 내용에 일치하는 리뷰를 찾아 반환할 수 있습니다.
//        return reviewRepository.findByContent(content);
//    }

    public List<Review> searchByTitleAndContentContaining(String keyword) {
        return reviewRepository.findByTitleContainingOrContentContaining(keyword, keyword);
    }

}
