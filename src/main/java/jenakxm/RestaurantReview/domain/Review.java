package jenakxm.RestaurantReview.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "restaurant", nullable = false)
    private String restaurant;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder
    public Review(String restaurant, String title, String content) {
        this.restaurant = restaurant;
        this.title = title;
        this.content = content;
    }

    public void update(String restaurant, String title, String content) {
        this.restaurant = restaurant;
        this.title = title;
        this.content = content;
    }
}
