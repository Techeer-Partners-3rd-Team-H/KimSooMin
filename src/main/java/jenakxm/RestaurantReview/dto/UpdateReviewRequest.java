package jenakxm.RestaurantReview.dto;

import jenakxm.RestaurantReview.domain.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateReviewRequest {
    private Restaurant restaurant;
    private String title;
    private String content;
    private LocalDateTime createdAt;
}
