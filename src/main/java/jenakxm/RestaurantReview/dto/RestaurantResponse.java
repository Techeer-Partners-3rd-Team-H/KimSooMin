package jenakxm.RestaurantReview.dto;

import jenakxm.RestaurantReview.domain.Restaurant;
import jenakxm.RestaurantReview.domain.Review;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RestaurantResponse {
    private Long restaurantId;
    private final String restaurantName;
    private final String category;

    public RestaurantResponse(Restaurant restaurant) {
        this.restaurantId = restaurant.getRestaurantId();
        this.restaurantName = restaurant.getRestaurantName();
        this.category = restaurant.getCategory();
    }
}
