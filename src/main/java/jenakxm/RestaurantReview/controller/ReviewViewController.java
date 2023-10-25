package jenakxm.RestaurantReview.controller;

import jenakxm.RestaurantReview.dto.ReviewListViewResponse;
import jenakxm.RestaurantReview.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ReviewViewController {
    private final ReviewService reviewService;

    @GetMapping("/reviews")
    public String getReviews(Model model) {
        List<ReviewListViewResponse> reviews = reviewService.findAll().stream()
                .map(ReviewListViewResponse::new)
                .toList();
        model.addAttribute("reviews", reviews);

        return "reviewList";
    }
}
