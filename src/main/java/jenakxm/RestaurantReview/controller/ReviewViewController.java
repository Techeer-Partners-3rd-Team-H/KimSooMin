package jenakxm.RestaurantReview.controller;

import jenakxm.RestaurantReview.domain.Review;
import jenakxm.RestaurantReview.dto.ReviewListViewResponse;
import jenakxm.RestaurantReview.dto.ReviewViewResponse;
import jenakxm.RestaurantReview.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/reviews/{id}")
    public String getReview(@PathVariable Long id, Model model) {
        Review review = reviewService.findById(id);
        model.addAttribute("review", new ReviewViewResponse(review));

        return "review";
    }

    @GetMapping("/new-review")
    // id 키를 가진 쿼리 파라미터의 값을 id 변수에 매핑 (id 없을 수도)
    public String newReview(@RequestParam(required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("review", new ReviewViewResponse());
        } else {
            Review review = reviewService.findById(id);
            model.addAttribute("review", new ReviewViewResponse(review));
        }
        return "newReview";
    }
}
