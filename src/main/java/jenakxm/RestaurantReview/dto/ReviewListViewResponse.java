package jenakxm.RestaurantReview.dto;

import jenakxm.RestaurantReview.domain.Review;
import lombok.Getter;

@Getter
public class ReviewListViewResponse {
    private final Long id;
    private final String restaurant;
    private final String title;
    private final String content;


    public ReviewListViewResponse(Review review) {
        this.id = review.getId();
        this.restaurant = review.getRestaurant();
        this.title = review.getTitle();
        this.content = review.getContent();
    }

}
