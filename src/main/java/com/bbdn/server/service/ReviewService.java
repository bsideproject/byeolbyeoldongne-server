package com.bbdn.server.service;

import com.bbdn.server.domain.entity.ReviewInfoEntity;
import com.bbdn.server.domain.interfaces.request.PlaceReviewModifyRequest;
import com.bbdn.server.domain.interfaces.response.CommonNotificationResponse;
import com.bbdn.server.repository.ReviewInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class ReviewService {

    final
    ReviewInfoRepository reviewInfoRepository;

    public ReviewService(ReviewInfoRepository reviewInfoRepository) {
        this.reviewInfoRepository = reviewInfoRepository;
    }

    public CommonNotificationResponse postLocationReview(PlaceReviewModifyRequest placeReviewModifyRequest) {

        ReviewInfoEntity reviewInfoEntity = ReviewInfoEntity.builder()
                .addressName((placeReviewModifyRequest.getAddressName()))
                .roadAddress(placeReviewModifyRequest.getRoadAddress())
                .x(placeReviewModifyRequest.getX())
                .y(placeReviewModifyRequest.getY())
                .reviewMainContent(placeReviewModifyRequest.getReviewMainContent())
                .reviewGoodContent(placeReviewModifyRequest.getReviewGoodContent())
                .reviewBadContent(placeReviewModifyRequest.getReviewBadContent())
                .trafficPoint(placeReviewModifyRequest.getTrafficPoint())
                .conveniencePoint(placeReviewModifyRequest.getConveniencePoint())
                .noisePoint(placeReviewModifyRequest.getNoisePoint())
                .safetyPoint(placeReviewModifyRequest.getSafetyPoint())
                .email(placeReviewModifyRequest.getEmail())
                .createdBy(placeReviewModifyRequest.getEmail())
                .createdAt(LocalDateTime.now())
                .build();

        CommonNotificationResponse commonNotificationResponse = new CommonNotificationResponse();

        try {
            reviewInfoRepository.save(reviewInfoEntity);
            commonNotificationResponse.setCode("0000");
            commonNotificationResponse.setMessage("성공적으로 저장되었습니다.");
        } catch (Exception e) {
            commonNotificationResponse.setCode("1000");
            commonNotificationResponse.setMessage("저장에 실패했습니다.");
        }

        return commonNotificationResponse;
    }

    public CommonNotificationResponse putLocationReview(PlaceReviewModifyRequest placeReviewModifyRequest) {

        ReviewInfoEntity reviewInfoEntity = ReviewInfoEntity.builder()
                .addressName((placeReviewModifyRequest.getAddressName()))
                .roadAddress(placeReviewModifyRequest.getRoadAddress())
                .x(placeReviewModifyRequest.getX())
                .y(placeReviewModifyRequest.getY())
                .reviewMainContent(placeReviewModifyRequest.getReviewMainContent())
                .reviewGoodContent(placeReviewModifyRequest.getReviewGoodContent())
                .reviewBadContent(placeReviewModifyRequest.getReviewBadContent())
                .trafficPoint(placeReviewModifyRequest.getTrafficPoint())
                .conveniencePoint(placeReviewModifyRequest.getConveniencePoint())
                .noisePoint(placeReviewModifyRequest.getNoisePoint())
                .safetyPoint(placeReviewModifyRequest.getSafetyPoint())
                .email(placeReviewModifyRequest.getEmail())
                .modifiedBy(placeReviewModifyRequest.getEmail())
                .modifiedAt(LocalDateTime.now())
                .build();

        CommonNotificationResponse commonNotificationResponse = new CommonNotificationResponse();

        try {
            reviewInfoRepository.save(reviewInfoEntity);
            commonNotificationResponse.setCode("0000");
            commonNotificationResponse.setMessage("성공적으로 수정되었습니다.");
        } catch (Exception e) {
            commonNotificationResponse.setCode("1000");
            commonNotificationResponse.setMessage("수정에 실패했습니다.");
        }

        return commonNotificationResponse;

    }
}
