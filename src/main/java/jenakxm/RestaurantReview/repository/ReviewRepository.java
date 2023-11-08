package jenakxm.RestaurantReview.repository;

import jenakxm.RestaurantReview.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByTitleContainingOrContentContaining(String title, String content);
    List<Review> findAllByOrderByCreatedAtAsc();
    List<Review> findAllByOrderByCreatedAtDesc();
}
