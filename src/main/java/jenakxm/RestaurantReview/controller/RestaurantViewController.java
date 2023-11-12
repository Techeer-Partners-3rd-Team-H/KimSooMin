package jenakxm.RestaurantReview.controller;

import jenakxm.RestaurantReview.domain.Restaurant;
import jenakxm.RestaurantReview.domain.Review;
import jenakxm.RestaurantReview.dto.RestaurantViewResponse;
import jenakxm.RestaurantReview.service.RestaurantService;
import jenakxm.RestaurantReview.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class RestaurantViewController {
    private final RestaurantService restaurantService;
    @GetMapping("/restaurants")
    public String getRestaurants(Model model, @PageableDefault(size = 5) Pageable pageable) {
        Page<Restaurant> restaurants = restaurantService.findAll(pageable);
        model.addAttribute("restaurants", restaurants);

        return "restaurantList";
    }

    @GetMapping("/restaurants/{id}")
    public String getRestaurant(@PathVariable Long restaurantId, Model model) {
        // Restaurant restaurant = restaurantService.findByRestaurantName(restaurantName);
        Restaurant restaurant = restaurantService.findByRestaurantId(restaurantId);
        model.addAttribute("restaurant", new RestaurantViewResponse(restaurant));

        return "restaurant";
    }

    @GetMapping("/new-restaurant")
    public String newRestaurant(@RequestParam(required = false) Long restaurantId, Model model) {
        if (restaurantId == null) {
            model.addAttribute("restaurant", new RestaurantViewResponse());
        } else {
            // Restaurant restaurant = restaurantService.findByRestaurantName(restaurantName);
            Restaurant restaurant = restaurantService.findByRestaurantId(restaurantId);
            model.addAttribute("restaurant", new RestaurantViewResponse(restaurant));
        }
        return "newRestaurant";
    }

}
