package jenakxm.RestaurantReview.dto;

public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException(String restaurantName) {
        super("Restaurant not found with name: " + restaurantName);
    }
}
