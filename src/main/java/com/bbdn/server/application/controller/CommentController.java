package com.bbdn.server.application.controller;

import com.bbdn.server.domain.interfaces.request.PlaceCommentUploadRequest;
import com.bbdn.server.domain.interfaces.response.CommonNotificationResponse;
import com.bbdn.server.domain.interfaces.response.PlaceCommentResponse;
import com.bbdn.server.handler.exception.IdNotFoundException;
import com.bbdn.server.service.ReviewCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/location")
public class CommentController {

    private final ReviewCommentService reviewCommentService;

    public CommentController(ReviewCommentService reviewCommentService) {
        this.reviewCommentService = reviewCommentService;
    }


    @PostMapping("/place/{placeId}/review/{reviewSequence}")
    public ResponseEntity postLocationReview(@PathVariable("placeId") Long placeId,
                                             @PathVariable("reviewSequence") Long reviewSequence,
                                             @RequestBody PlaceCommentUploadRequest placeCommentUploadRequest) {

        log.info("postLocationReview request : " + placeCommentUploadRequest.toString());
        if (StringUtils.isEmpty(placeId) ||
                StringUtils.isEmpty(reviewSequence)) {
            throw new IdNotFoundException("ID는 필수 입력 값 입니다.");
        }

        CommonNotificationResponse commonNotificationResponse =
                reviewCommentService.postReviewComment(placeId, reviewSequence, placeCommentUploadRequest);

        return ResponseEntity.ok(commonNotificationResponse);
    }

    @GetMapping("/review/comment")
    public ResponseEntity getLocationReview(@RequestParam Long placeId,
                                            @RequestParam Long reviewSequence) {

        if (StringUtils.isEmpty(placeId) ||
                StringUtils.isEmpty(reviewSequence)) {
            throw new IdNotFoundException("ID는 필수 입력 값 입니다.");
        }

        PlaceCommentResponse placeReviewResponseList = reviewCommentService.getLocationReviewCommentList(placeId, reviewSequence);

        return ResponseEntity.ok(placeReviewResponseList);
    }
}
