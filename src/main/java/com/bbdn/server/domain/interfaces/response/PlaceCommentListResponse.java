package com.bbdn.server.domain.interfaces.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PlaceCommentListResponse {

    private String roadAddress; // 도로명
    private String reviewSequence; // 리뷰 일련번호
    private String commentSequence; // 댓글 일련번호
    private String commentContent; // 댓글 내용
    private int likeCount; // 좋아요 수
    private String createdBy; // 댓글 작성자
    private LocalDateTime createdAt; // 댓글 작성 시점
}
