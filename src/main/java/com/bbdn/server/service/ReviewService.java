package com.bbdn.server.service;

import com.bbdn.server.domain.entity.ReviewInfoEntity;
import com.bbdn.server.domain.interfaces.request.PlaceReviewModifyRequest;
import com.bbdn.server.domain.interfaces.request.PlaceReviewUploadRequest;
import com.bbdn.server.domain.interfaces.response.CommonNotificationResponse;
import com.bbdn.server.domain.interfaces.response.PlaceReviewResponse;
import com.bbdn.server.repository.ReviewInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ReviewService {

    final
    ReviewInfoRepository reviewInfoRepository;

    public ReviewService(ReviewInfoRepository reviewInfoRepository) {
        this.reviewInfoRepository = reviewInfoRepository;
    }

    public CommonNotificationResponse postLocationReview(PlaceReviewUploadRequest placeReviewUploadRequest) {

        ReviewInfoEntity reviewInfoEntity = ReviewInfoEntity.builder()
                .placeId(placeReviewUploadRequest.getPlaceId())
                .addressName((placeReviewUploadRequest.getAddressName()))
                .roadAddress(placeReviewUploadRequest.getRoadAddress())
                .x(placeReviewUploadRequest.getX())
                .y(placeReviewUploadRequest.getY())
                .reviewMainContent(placeReviewUploadRequest.getReviewMainContent())
                .reviewGoodContent(placeReviewUploadRequest.getReviewGoodContent())
                .reviewBadContent(placeReviewUploadRequest.getReviewBadContent())
                .trafficPoint(placeReviewUploadRequest.getTrafficPoint())
                .conveniencePoint(placeReviewUploadRequest.getConveniencePoint())
                .noisePoint(placeReviewUploadRequest.getNoisePoint())
                .safetyPoint(placeReviewUploadRequest.getSafetyPoint())
                .email(placeReviewUploadRequest.getEmail())
                .createdBy(placeReviewUploadRequest.getEmail())
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

    public CommonNotificationResponse putLocationReview(Long reviewSequence,
                                                        PlaceReviewModifyRequest placeReviewModifyRequest) {

        Optional<ReviewInfoEntity> reviewInfo =
                reviewInfoRepository.findById(reviewSequence);

        log.info("putLocationReview reviewInfo : " + reviewInfo.get().toString());

        CommonNotificationResponse commonNotificationResponse = new CommonNotificationResponse();

        if(reviewInfo.isPresent()) {
            try {
                ReviewInfoEntity reviewInfoEntity = reviewInfo.get();

                reviewInfoEntity.setId(reviewSequence);
                reviewInfoEntity.setAddressName((placeReviewModifyRequest.getAddressName()));
                reviewInfoEntity.setRoadAddress(placeReviewModifyRequest.getRoadAddress());
                reviewInfoEntity.setX(placeReviewModifyRequest.getX());
                reviewInfoEntity.setY(placeReviewModifyRequest.getY());
                reviewInfoEntity.setReviewMainContent(placeReviewModifyRequest.getReviewMainContent());
                reviewInfoEntity.setReviewGoodContent(placeReviewModifyRequest.getReviewGoodContent());
                reviewInfoEntity.setReviewBadContent(placeReviewModifyRequest.getReviewBadContent());
                reviewInfoEntity.setTrafficPoint(placeReviewModifyRequest.getTrafficPoint());
                reviewInfoEntity.setConveniencePoint(placeReviewModifyRequest.getConveniencePoint());
                reviewInfoEntity.setNoisePoint(placeReviewModifyRequest.getNoisePoint());
                reviewInfoEntity.setSafetyPoint(placeReviewModifyRequest.getSafetyPoint());
                reviewInfoEntity.setEmail(placeReviewModifyRequest.getEmail());
                reviewInfoEntity.setModifiedBy(placeReviewModifyRequest.getEmail());
                reviewInfoEntity.setModifiedAt(LocalDateTime.now());

                log.info("putLocationReview reviewInfoEntity : " + reviewInfoEntity.toString());
                reviewInfoRepository.save(reviewInfoEntity);

            } catch (Exception e) {
                commonNotificationResponse.setCode("1000");
                commonNotificationResponse.setMessage("수정에 실패했습니다.");
            }

            commonNotificationResponse.setCode("0000");
            commonNotificationResponse.setMessage("성공적으로 수정되었습니다.");
        }

        return commonNotificationResponse;
    }

    public List<PlaceReviewResponse> getLocationReviewList(Long placeId) {

        List<ReviewInfoEntity> reviewInfoEntityList = reviewInfoRepository.findByPlaceId(placeId);

        log.info("placeReviewList: " + reviewInfoEntityList.toString());

        List<PlaceReviewResponse> placeReviewResponseList = new ArrayList<>();

        reviewInfoEntityList.forEach(placeReview -> {
            PlaceReviewResponse placeReviewResponse = PlaceReviewResponse.builder()
                    .reviewSequence(placeReview.getId().toString())
                    .reviewMainContent(placeReview.getReviewMainContent())
                    .reviewGoodContent(placeReview.getReviewGoodContent())
                    .reviewBadContent(placeReview.getReviewBadContent())
                    .trafficPoint(placeReview.getTrafficPoint())
                    .conveniencePoint(placeReview.getConveniencePoint())
                    .noisePoint(placeReview.getNoisePoint())
                    .safetyPoint(placeReview.getSafetyPoint())
                    .createdBy(placeReview.getCreatedBy())
                    .createdAt(placeReview.getCreatedAt())
                    .modifiedBy(placeReview.getModifiedBy())
                    .modifiedAt(placeReview.getModifiedAt())
                    .build();

            placeReviewResponseList.add(placeReviewResponse);
        });

        return placeReviewResponseList;
    }

    public int deleteLocationReview(Long reviewId) {
        int result;
        try {
            reviewInfoRepository.deleteById(reviewId);
            result = 1;
        } catch(Exception e) {
            result = 0;
            log.error(e.getMessage());
        }
        return result;
    }
}
