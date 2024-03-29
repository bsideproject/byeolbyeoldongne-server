package com.bbdn.server.application.controller;

import com.bbdn.server.domain.interfaces.request.PlaceReviewModifyRequest;
import com.bbdn.server.domain.interfaces.request.PlaceReviewUploadRequest;
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

    @PostMapping("/place/review/{placeId}")
    public ResponseEntity postLocationReview(@PathVariable("placeId") Long placeId,
                                             @RequestBody PlaceReviewUploadRequest placeReviewUploadRequest) {

        log.info("postLocationReview request : " + placeReviewUploadRequest);
        if (StringUtils.isEmpty(placeId)) {
            throw new IdNotFoundException("ID는 필수 입력 값 입니다.");
        }

        //TODO: timestamp 제대로 찍히는지 확인 필요
        //TODO: 작성자가 이미 등록한 후기인 경우 block 처리
        placeReviewUploadRequest.setPlaceId(placeId);

        CommonNotificationResponse commonNotificationResponse =
                reviewService.postLocationReview(placeReviewUploadRequest);

        return ResponseEntity.ok(commonNotificationResponse);
    }

    @PutMapping("/place/review/{reviewSequence}")
    public ResponseEntity putLocationReview(
            @PathVariable("reviewSequence") Long reviewSequence,
            @RequestBody PlaceReviewModifyRequest placeReviewModifyRequest) {

        log.info("putLocationReview reviewSequence : " + reviewSequence);

        if (StringUtils.isEmpty(reviewSequence)) {
            throw new IdNotFoundException("ID는 필수 입력 값 입니다.");
        }

        log.info("putLocationReview request : " + placeReviewModifyRequest);

        CommonNotificationResponse commonNotificationResponse =
                reviewService.putLocationReview(reviewSequence, placeReviewModifyRequest);

        return ResponseEntity.ok(commonNotificationResponse);
    }

    @GetMapping("/review")
    public ResponseEntity getLocationReview(@RequestParam Long placeId) {

        if (StringUtils.isEmpty(placeId)) {
            throw new IdNotFoundException("ID는 필수 입력 값 입니다.");
        }

        List<PlaceReviewResponse> placeReviewResponseList = reviewService.getLocationReviewListByPlaceId(placeId);

        return ResponseEntity.ok(placeReviewResponseList);
    }

    @DeleteMapping("/review")
    public ResponseEntity deleteLocationReview(@RequestParam Long reviewSequence) {

        if (StringUtils.isEmpty(reviewSequence)) {
            throw new IdNotFoundException("ID는 필수 입력 값 입니다.");
        }
        int result = reviewService.deleteLocationReview(reviewSequence);
        return ResponseEntity.ok(result);
    }
}
