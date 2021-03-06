package com.bbdn.server.service;

import com.bbdn.server.domain.entity.CommentInfoEntity;
import com.bbdn.server.domain.interfaces.request.PlaceCommentUploadRequest;
import com.bbdn.server.domain.interfaces.response.CommonNotificationResponse;
import com.bbdn.server.domain.interfaces.response.PlaceCommentResponse;
import com.bbdn.server.repository.CommentInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ReviewCommentService {

    final CommentInfoRepository commentInfoRepository;

    public ReviewCommentService(CommentInfoRepository commentInfoRepository) {
        this.commentInfoRepository = commentInfoRepository;
    }


    public CommonNotificationResponse postReviewComment(Long placeId,
                                                        Long reviewSequence,
                                                        PlaceCommentUploadRequest placeCommentUploadRequest) {

        CommonNotificationResponse commonNotificationResponse = new CommonNotificationResponse();

        try {
            CommentInfoEntity commentInfoEntity = CommentInfoEntity.builder()
                    .placeId(placeId)
                    .reviewSequence(reviewSequence)
                    .commentContent(placeCommentUploadRequest.getComment_content())
                    .createdBy(placeCommentUploadRequest.getEmail())
                    .createdAt(LocalDateTime.now())
                    .build();

            commentInfoRepository.save(commentInfoEntity);

        } catch (Exception e) {
            commonNotificationResponse.setCode("1000");
            commonNotificationResponse.setMessage("수정에 실패했습니다.");
        }

        commonNotificationResponse.setCode("0000");
        commonNotificationResponse.setMessage("성공적으로 저장되었습니다.");

        return commonNotificationResponse;
    }

    public PlaceCommentResponse getLocationReviewCommentList(Long placeId,
                                                                   Long reviewSequence) {

        List<CommentInfoEntity> commentInfoEntityList = commentInfoRepository.findByPlaceIdAndReviewSequence(placeId, reviewSequence);

        PlaceCommentResponse placeCommentResponse = PlaceCommentResponse.builder()
                .placeId(placeId)
                .reviewSequence(reviewSequence)
                .build();

        if(commentInfoEntityList.size() > 0) {

            List<PlaceCommentResponse.CommentInfo> commentInfoList = new ArrayList<>();

            for(CommentInfoEntity commentInfoEntity : commentInfoEntityList) {

                long likeCount = commentInfoEntity.getLikeCount();

                PlaceCommentResponse.CommentInfo commentInfo = PlaceCommentResponse.CommentInfo.builder()
                        .commentSequence(commentInfoEntity.getCommentSequence())
                        .commentContent(commentInfoEntity.getCommentContent())
                        .likeCount(likeCount)
                        .createdBy(commentInfoEntity.getCreatedBy())
                        .createdAt(commentInfoEntity.getCreatedAt())
                        .build();

                commentInfoList.add(commentInfo);
            }

            placeCommentResponse.setCommentInfoList(commentInfoList);
        }

        return placeCommentResponse;
    }
}
