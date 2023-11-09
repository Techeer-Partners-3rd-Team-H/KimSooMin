package jenakxm.RestaurantReview.repository;

import jenakxm.RestaurantReview.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByTitleContainingOrContentContaining(String title, String content);
    List<Review> findAllByOrderByCreatedAtAsc();
    List<Review> findAllByOrderByCreatedAtDesc();

    @Override
    Page<Review> findAll(Pageable pageable);

    Page<Review> findAllByOrderByCreatedAtAsc(Pageable pageable);

    Page<Review> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
