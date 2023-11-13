package jenakxm.RestaurantReview.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Table(name = "REVIEW")
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_name", referencedColumnName = "restaurant_name", nullable = false)
    @JsonManagedReference
    private Restaurant restaurant;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public Review(String restaurant, String title, String content, LocalDateTime createdAt) {
        this.restaurant = new Restaurant(restaurant, null, false);
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public void update(Restaurant restaurant, String title, String content) {
        this.restaurant = restaurant;
        this.title = title;
        this.content = content;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
