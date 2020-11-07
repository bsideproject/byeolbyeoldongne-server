package com.bbdn.server.domain.interfaces.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PlaceCommentReplyListRequest {

    private String id; // 장소ID
    private String addressName; // 전체도로명주소
    private String roadAddress; // 도로명주소
    private Long x; // x좌표
    private Long y; // y좌표
    private String reviewSequence; // 리뷰 일련번호
    private String commentSequence; // 댓글 일련번호
    
}
