package com.bbdn.server.domain.entity;

import lombok.*;
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
public class ContentInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String roadName; // 도로명
    String mainBuildingNo; // 건물본번
    String zoneNo; // zone 번호
    String reviewSequence; //리뷰 일련번호

    String deleteYn; // 삭제 여부 TODO: Enum 으로 변경?

    String reviewMainContent; // 리뷰 개요
    String reviewGoodContent; // 장점 리뷰 내용
    String reviewBadContent; // 단점 리뷰 내용

    double trafficPoint; // 교통접근성 점수
    double conveniencePoint; // 편의성 점수
    double noisePoint; // 소음 점수
    double safetyPoint; // 치안 점수

    int reportCount; // 신고 횟수

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedBy
    private String modifiedBy;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    // OrderDetail N : 1 OrderGroup
    @ManyToOne
    private UserEntity userEntity;
}
