package jenakxm.RestaurantReview.dto;

import jenakxm.RestaurantReview.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddReviewRequest {
    private String restaurant;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public Review toEntity() {
        return Review.builder()
                .restaurant(restaurant)
                .title(title)
                .content(content)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
