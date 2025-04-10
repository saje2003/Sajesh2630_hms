package com.hmsapp.controller;

import com.hmsapp.entity.Property;
import com.hmsapp.entity.Reviews;
import com.hmsapp.entity.User;
import com.hmsapp.repository.PropertyRepository;
import com.hmsapp.repository.ReviewsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    private PropertyRepository propertyRepository;
    private ReviewsRepository reviewsRepository;

    public ReviewController(PropertyRepository propertyRepository, ReviewsRepository reviewRepository) {
        this.propertyRepository = propertyRepository;
        this.reviewsRepository = reviewRepository;
    }

    @PostMapping
    public String addReview(
            @RequestBody Reviews review,
            @RequestParam long propertyId,
            @AuthenticationPrincipal User user
    ) {
           Property property = propertyRepository.findById(propertyId).get();

        Reviews reviewsStatus = reviewsRepository.findByPropertyAndUser(property, user);

        if(reviewsStatus != null) {

           review.setProperty(property);
           review.setUser(user);
           reviewsRepository.save(review);
           return "added..";
       }
       return "review already given";
    }

    @GetMapping("user/reviews")
    public List<Reviews> viewMyReviews(
            @AuthenticationPrincipal User user
    ) {
        return reviewsRepository.findByUser(user);
    }
}
