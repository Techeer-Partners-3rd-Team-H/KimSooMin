package jenakxm.RestaurantReview.dto;

import jenakxm.RestaurantReview.domain.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@Getter
public class ReviewViewResponse {
    private Long id;
    private String restaurant;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public ReviewViewResponse(Review review) {
        this.id = review.getId();
        this.restaurant = review.getRestaurant();
        this.title = review.getTitle();
        this.content = review.getContent();
    }
}
