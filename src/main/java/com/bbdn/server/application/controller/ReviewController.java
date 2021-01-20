package com.bbdn.server.application.controller;

import com.bbdn.server.domain.interfaces.request.PlaceReviewModifyRequest;
import com.bbdn.server.domain.interfaces.response.CommonNotificationResponse;
import com.bbdn.server.service.FormatTransformService;
import com.bbdn.server.service.KakaoPlaceService;
import com.bbdn.server.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/review")
public class ReviewController {

    final KakaoPlaceService kakaoPlaceService;
    final FormatTransformService formatTransformService;
    final ReviewService reviewService;

    public ReviewController(KakaoPlaceService kakaoPlaceService,
                            FormatTransformService formatTransformService,
                            ReviewService reviewService) {
        this.kakaoPlaceService = kakaoPlaceService;
        this.formatTransformService = formatTransformService;
        this.reviewService = reviewService;
    }

    @PostMapping("/")
    public ResponseEntity postLocationReview(@RequestBody PlaceReviewModifyRequest placeReviewModifyRequest) {

        log.info("postLocationReview request : " + placeReviewModifyRequest);
        CommonNotificationResponse commonNotificationResponse = reviewService.postLocationReview(placeReviewModifyRequest);

        return ResponseEntity.ok(commonNotificationResponse);
    }
}
