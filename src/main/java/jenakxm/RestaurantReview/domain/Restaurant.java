package jenakxm.RestaurantReview.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "RESTAURANT")
@NoArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id", updatable = false, unique = true)
    private Long restaurantId;

    @Column(name = "restaurant_name", unique = true)
    private String restaurantName;

    @Column(name = "category")
    private String category;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonBackReference
    private List<Review> reviews = new ArrayList<>();;

    @Column(name = "deleted", columnDefinition = "BOOLEAN DEFAULT false")
    private boolean deleted; // 삭제 플래그

    @Builder
    public Restaurant(String restaurantName, String category, boolean deleted) {
        this.restaurantName = restaurantName;
        this.category = category;
        this.deleted = deleted;
    }

    // 카테고리만 변경 가능하므로 레스토랑 이름x
    public void update(String category) {
        this.category = category;
    }

    // Soft Delete 메서드
    public void softDelete() {
        this.deleted = true;
    }

    // 레스토랑에 리뷰 추가 메서드
    public void addReview(Review review) {
        reviews.add(review);
        review.setRestaurant(this);
    }

    // 레스토랑에서 리뷰 제거 메서드
    public void removeReview(Review review) {
        reviews.remove(review);
        review.setRestaurant(null);
    }
}
