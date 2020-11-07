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
public class PlaceCommentReplyListResponse {

    private String reviewSequence;	// 리뷰 일련번호
    private String commentSequence; // 댓글 일련번호
    private String replySequence; // 대댓글 일련번호
    private String replyContent; // 대댓글 내용
    private int  likeCount; // 좋아요 수
    private String createdBy; // 대댓글작성자
    private LocalDateTime createdAt; // 대댓글작성시점
}
