package com.bbdn.server.application.controller;

import com.bbdn.server.domain.interfaces.response.PlaceReviewResponse;
import com.bbdn.server.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/my-page")
public class MyPageController {

    private final ReviewService reviewService;

    public MyPageController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @RequestMapping("/review")
    public ResponseEntity getReviewListByEmail(@RequestParam("email") String email) {

        List<PlaceReviewResponse> placeReviewResponseList = reviewService.getLocationReviewListByEmail(email);

        return ResponseEntity.ok(placeReviewResponseList);
    }
}
