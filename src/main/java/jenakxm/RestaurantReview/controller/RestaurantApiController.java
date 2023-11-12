package jenakxm.RestaurantReview.controller;

import jenakxm.RestaurantReview.domain.Restaurant;
import jenakxm.RestaurantReview.domain.Review;
import jenakxm.RestaurantReview.dto.*;
import jenakxm.RestaurantReview.service.RestaurantService;
import jenakxm.RestaurantReview.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/restaurants")
public class RestaurantApiController {
    private final RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody AddRestaurantRequest request) {
        Restaurant savedRestaurant = restaurantService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRestaurant);
    }

    @GetMapping("/{restaurantName}")
    public ResponseEntity<?> findRestaurant(@PathVariable String restaurantName) {
        try {
            Restaurant restaurant = restaurantService.findByRestaurantName(restaurantName);
            return ResponseEntity.ok().body(new RestaurantResponse(restaurant));
        } catch (RestaurantNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurant not found");
        }
    }

    @DeleteMapping("/{restaurantName}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable String name) {
        try {
            restaurantService.deleteByRestaurantName(name);
            return ResponseEntity.ok().build();
        } catch (RestaurantNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurant not found");
        }
    }

    @PutMapping("/{restaurantName}")
    public ResponseEntity<?> updateRestaurant(@PathVariable String restaurantName, @RequestBody UpdateRestaurantRequest request) {
        try {
            Restaurant updatedRestaurant = restaurantService.update(restaurantName, request);
            return ResponseEntity.ok().body(updatedRestaurant);
        } catch (RestaurantNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurant not found");
        }
    }

    @GetMapping
    public Page<Restaurant> getRestaurants(
            @RequestParam(name = "sort", required = false, defaultValue = "desc") String sortOption,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size
    ) {
        Pageable pageable;

        if ("oldest".equals(sortOption)) {
            // 오래된순 정렬
            pageable = PageRequest.of(page, size, Sort.by("createdAt").ascending());
        } else {
            // 최신순 정렬 (기본값)
            pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        }
        return restaurantService.findAll(pageable);
    }
}
