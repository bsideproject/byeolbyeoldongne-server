package com.bbdn.server.application.controller;

import com.bbdn.server.domain.interfaces.request.PlaceReviewModifyRequest;
import com.bbdn.server.domain.interfaces.response.CommonNotificationResponse;
import com.bbdn.server.domain.interfaces.response.PlaceReviewResponse;
import com.bbdn.server.handler.exception.IdNotFoundException;
import com.bbdn.server.service.FormatTransformService;
import com.bbdn.server.service.KakaoPlaceService;
import com.bbdn.server.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/review/{placeId}")
    public ResponseEntity postLocationReview(@PathVariable("placeId") String placeId,
                                             @RequestBody PlaceReviewModifyRequest placeReviewModifyRequest) {

        log.info("postLocationReview request : " + placeReviewModifyRequest);
        if (StringUtils.isEmpty(placeId)) {
            throw new IdNotFoundException("ID는 필수 입력 값 입니다.");
        }

        //TODO: timestamp 제대로 찍히는지 확인 필요
        //TODO: 작성자가 이미 등록한 후기인 경우 block 처리
        placeReviewModifyRequest.setPlaceId(placeId);

        CommonNotificationResponse commonNotificationResponse =
                reviewService.postLocationReview(placeReviewModifyRequest);

        return ResponseEntity.ok(commonNotificationResponse);
    }

    @PutMapping("/review/{placeId}")
    public ResponseEntity putLocationReview(@PathVariable("placeId") String placeId,
                                            @RequestBody PlaceReviewModifyRequest placeReviewModifyRequest) {

        log.info("putLocationReview request : " + placeReviewModifyRequest);
        if (StringUtils.isEmpty(placeId)) {
            throw new IdNotFoundException("ID는 필수 입력 값 입니다.");
        }

        placeReviewModifyRequest.setPlaceId(placeId);

        CommonNotificationResponse commonNotificationResponse =
                reviewService.putLocationReview(placeReviewModifyRequest);

        return ResponseEntity.ok(commonNotificationResponse);
    }

    @GetMapping("/review")
    public ResponseEntity getLocationReview(@RequestParam String placeId) {

        if (StringUtils.isEmpty(placeId)) {
            throw new IdNotFoundException("ID는 필수 입력 값 입니다.");
        }

        List<PlaceReviewResponse> placeReviewResponseList = reviewService.getLocationReviewList(placeId);

        return ResponseEntity.ok(placeReviewResponseList);
    }

    @DeleteMapping("/review")
    public ResponseEntity deleteLocationReview(@RequestParam String reviewSequence) {

        if (StringUtils.isEmpty(reviewSequence)) {
            throw new IdNotFoundException("ID는 필수 입력 값 입니다.");
        }
        int result = reviewService.deleteLocationReview(reviewSequence);
        return ResponseEntity.ok(result);
    }
}
