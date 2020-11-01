package com.bbdn.server.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Accessors(chain = true)
public class CommentInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String commentSequence; // 댓글 일련번호

    String deleteYn; // 삭제 여부 TODO: Enum 으로 변경?

    String roadName; // 도로명
    String mainBuildingNo; // 건물본번
    String zoneNo; // zone 번호
    String reviewSequence; //리뷰 일련번호
    String email; // email

    String commentContent; // 댓글 내용
    Long likeCount; // 좋아요 수

    int reportCount; // 신고 횟수

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedBy
    private String modifiedBy;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

}
