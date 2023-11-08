package jenakxm.RestaurantReview.repository;

import jenakxm.RestaurantReview.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {


//    // 제목과 내용으로 리뷰 검색
//    List<Review> findByTitleAndContent(String title, String content);

    List<Review> findByTitleContainingOrContentContaining(String title, String content);
}
