package jenakxm.RestaurantReview.repository;

import jenakxm.RestaurantReview.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
