package jenakxm.RestaurantReview.service;

import jenakxm.RestaurantReview.domain.Restaurant;
import jenakxm.RestaurantReview.dto.AddRestaurantRequest;
import jenakxm.RestaurantReview.dto.UpdateRestaurantRequest;
import jenakxm.RestaurantReview.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    public Restaurant save(AddRestaurantRequest request) {
        return restaurantRepository.save(request.toEntity());
    }

    public Restaurant findByRestaurantName(String restaurantName) {
        return restaurantRepository.findByRestaurantName(restaurantName);
    }

    public void deleteByRestaurantName(String restaurantName) {
        restaurantRepository.deleteByRestaurantName(restaurantName);
    }

    @Transactional
    public Restaurant update(String restaurantName, UpdateRestaurantRequest request) {
        Restaurant restaurant = restaurantRepository.findByRestaurantName(restaurantName);

        restaurant.update(request.getCategory());
        return restaurant;
    }

    public Page<Restaurant> findAll(Pageable pageable) {
        return restaurantRepository.findAll(pageable);
    }

    public Restaurant findByRestaurantId(Long restaurantId) {
        return restaurantRepository.findByRestaurantId(restaurantId);
    }
}
