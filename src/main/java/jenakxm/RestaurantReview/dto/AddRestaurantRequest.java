package jenakxm.RestaurantReview.dto;

import jenakxm.RestaurantReview.domain.Restaurant;
import jenakxm.RestaurantReview.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddRestaurantRequest {
    private String restaurantName;
    private String category;
    private boolean deleted;

    public Restaurant toEntity() {
        return Restaurant.builder()
                .restaurantName(restaurantName)
                .category(category)
                .deleted(false)
                .build();
    }
}
