package jenakxm.RestaurantReview.repository;

import jenakxm.RestaurantReview.domain.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    // Restaurant findByName(String name);
    Restaurant findByRestaurantName(String restaurantName);

    //void deleteByName(String name);
    void deleteByRestaurantName(String restaurantName);

    @Override
    Page<Restaurant> findAll(Pageable pageable);

    Restaurant findByRestaurantId(Long restaurantId);

    void deleteByRestaurantId(Long restaurantId);
}
