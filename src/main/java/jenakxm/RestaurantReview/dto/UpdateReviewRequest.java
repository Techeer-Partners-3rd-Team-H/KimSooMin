package jenakxm.RestaurantReview.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateReviewRequest {
    private String restaurant;
    private String title;
    private String content;
}
