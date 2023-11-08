package jenakxm.RestaurantReview.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

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

//    @CreatedDate
//    @Column(name="created_at")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

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
