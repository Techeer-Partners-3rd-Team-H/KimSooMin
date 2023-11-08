package jenakxm.RestaurantReview.dto;

import jenakxm.RestaurantReview.domain.Review;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewResponse {
    private final String restaurant;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;

    public ReviewResponse(Review review) {
        this.restaurant = review.getRestaurant();
        this.title = review.getTitle();
        this.content = review.getContent();
        this.createdAt = review.getCreatedAt();
    }
}
