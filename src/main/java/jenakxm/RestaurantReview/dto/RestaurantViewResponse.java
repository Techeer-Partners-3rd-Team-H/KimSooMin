package jenakxm.RestaurantReview.dto;

import jenakxm.RestaurantReview.domain.Restaurant;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class RestaurantViewResponse {
    private Long restaurantId;
    private String restaurantName;
    private String category;

    public RestaurantViewResponse(Restaurant restaurant) {
        this.restaurantId = restaurant.getRestaurantId();
        this.restaurantName = restaurant.getRestaurantName();
        this.category = restaurant.getCategory();
    }
}
