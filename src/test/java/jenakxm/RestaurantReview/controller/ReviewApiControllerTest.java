package jenakxm.RestaurantReview.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jenakxm.RestaurantReview.domain.Review;
import jenakxm.RestaurantReview.dto.AddReviewRequest;
import jenakxm.RestaurantReview.dto.UpdateReviewRequest;
import jenakxm.RestaurantReview.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.assertj.AssertableApplicationContext.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ReviewApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    ReviewRepository reviewRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        reviewRepository.deleteAll();
    }

    @DisplayName("addReview : 리뷰 생성")
    @Test
    void addReview() throws Exception {
        // given
        final String url = "/api/reviews";
        final String restaurant = "restaurant";
        final String title = "title";
        final String content = "content";
        final AddReviewRequest addReviewRequest = new AddReviewRequest(restaurant, title, content);

        final String requestBody = objectMapper.writeValueAsString(addReviewRequest);

        // when
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        // then
        result.andExpect(status().isCreated());

        List<Review> reviews = reviewRepository.findAll();

        assertThat(reviews.size()).isEqualTo(1);
        assertThat(reviews.get(0).getRestaurant()).isEqualTo(restaurant);
        assertThat(reviews.get(0).getTitle()).isEqualTo(title);
        assertThat(reviews.get(0).getContent()).isEqualTo(content);
    }

    @DisplayName("findAllReviews : 리뷰 목록 조회")
    @Test
    void findAllReviews() throws Exception {
        // given
        final String url = "/api/reviews";
        final String restaurant = "restaurant";
        final String title = "title";
        final String content = "content";

        reviewRepository.save(Review.builder()
                .restaurant(restaurant)
                .title(title)
                .content(content)
                .build());

        // when
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].restaurant").value(restaurant))
                .andExpect(jsonPath("$[0].title").value(title))
                .andExpect(jsonPath("$[0].content").value(content));
    }

    @DisplayName("findReview : 리뷰 조회")
    @Test
    void findReview() throws Exception {
        // given
        final String url = "/api/reviews/{id}";
        final String restaurant = "restaurant";
        final String title = "title";
        final String content = "content";

        Review savedReview = reviewRepository.save(Review.builder()
                .restaurant(restaurant)
                .title(title)
                .content(content)
                .build());

        // when
        final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(url, savedReview.getId()));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.restaurant").value(restaurant))
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.content").value(content));
    }

    @DisplayName("deleteReview : 리뷰 삭제")
    @Test
    void deleteReview() throws Exception {
        // given
        final String url = "/api/reviews/{id}";
        final String restaurant = "restaurant";
        final String title = "title";
        final String content = "content";

        Review savedReview = reviewRepository.save(Review.builder()
                .restaurant(restaurant)
                .title(title)
                .content(content)
                .build());

        // when
        mockMvc.perform(delete(url, savedReview.getId()))
                .andExpect(status().isOk());

        // then
        List<Review> reviews = reviewRepository.findAll();

        assertThat(reviews).isEmpty();
    }

    @DisplayName("updateReview : 리뷰 수정")
    @Test
    void updateReview() throws Exception {
        // given
        final String url = "/api/reviews/{id}";
        final String restaurant = "restaurant";
        final String title = "title";
        final String content = "content";

        Review savedReview = reviewRepository.save(Review.builder()
                .restaurant(restaurant)
                .title(title)
                .content(content)
                .build());

        final String newRestaurant = "new restaurant";
        final String newTitle = "new title";
        final String newContent = "new content";

        UpdateReviewRequest request = new UpdateReviewRequest(newRestaurant, newTitle, newContent);

        // when
        ResultActions result = mockMvc.perform(put(url, savedReview.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)));


        // then
        result.andExpect(status().isOk());

        Review review = reviewRepository.findById(savedReview.getId()).get();

        assertThat(review.getRestaurant()).isEqualTo(newRestaurant);
        assertThat(review.getTitle()).isEqualTo(newTitle);
        assertThat(review.getContent()).isEqualTo(newContent);
    }
}