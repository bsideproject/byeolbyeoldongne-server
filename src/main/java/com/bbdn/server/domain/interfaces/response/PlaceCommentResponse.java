package com.bbdn.server.domain.interfaces.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PlaceCommentResponse {

    private long placeId; // 장소ID
    private long reviewSequence; // 리뷰 일련번호

    private List<CommentInfo> commentInfoList;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    public static class CommentInfo {
        private long commentSequence; // 댓글 일련번호
        private String commentContent; // 댓글 내용
        private long likeCount; // 좋아요 수
        private String createdBy; // 댓글 작성자
        private LocalDateTime createdAt; // 댓글 작성 시점
    }

}
