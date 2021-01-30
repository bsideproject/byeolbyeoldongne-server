package com.bbdn.server.application.controller;

import com.bbdn.server.domain.interfaces.request.PlaceReviewModifyRequest;
import com.bbdn.server.domain.interfaces.response.bbdn.CommonNotificationResponse;
import com.bbdn.server.handler.exception.IdNotFoundException;
import com.bbdn.server.service.FormatTransformService;
import com.bbdn.server.service.KakaoPlaceService;
import com.bbdn.server.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/location")
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

    @PostMapping("/review/{id}")
    public ResponseEntity postLocationReview(@PathVariable("id") String id,
                                             @RequestBody PlaceReviewModifyRequest placeReviewModifyRequest) {

        log.info("postLocationReview request : " + placeReviewModifyRequest);
        if(StringUtils.isEmpty(id)) {
            throw new IdNotFoundException("ID는 필수 입력 값 입니다.");
        }
        CommonNotificationResponse commonNotificationResponse = reviewService.postLocationReview(placeReviewModifyRequest);

        return ResponseEntity.ok(commonNotificationResponse);
    }

    @PutMapping("/review/{id}")
    public ResponseEntity putLocationReview(@PathVariable("id") String id,
                                            @RequestBody PlaceReviewModifyRequest placeReviewModifyRequest) {

        log.info("putLocationReview request : " + placeReviewModifyRequest);
        if(StringUtils.isEmpty(id)) {
            throw new IdNotFoundException("ID는 필수 입력 값 입니다.");
        }
        CommonNotificationResponse commonNotificationResponse = reviewService.putLocationReview(placeReviewModifyRequest);

        return ResponseEntity.ok(commonNotificationResponse);
    }
}
