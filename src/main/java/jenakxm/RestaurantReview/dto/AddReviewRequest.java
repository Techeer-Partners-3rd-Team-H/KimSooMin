package jenakxm.RestaurantReview.dto;

import jenakxm.RestaurantReview.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddReviewRequest {
    private String restaurant;
    private String title;
    private String content;

    public Review toEntity() {
        return Review.builder()
                .restaurant(restaurant)
                .title(title)
                .content(content)
                .build();
    }
}
