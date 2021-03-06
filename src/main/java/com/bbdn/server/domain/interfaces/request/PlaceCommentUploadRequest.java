package com.bbdn.server.domain.interfaces.request;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PlaceCommentUploadRequest {

    private String comment_content; // 댓글 내용
    private String email; // 댓글 작성자

}
