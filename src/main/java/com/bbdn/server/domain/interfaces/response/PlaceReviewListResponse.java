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
public class PlaceReviewListResponse {

    private String reviewSequence; // 리뷰 일련번호
    private String reviewMainContent; // 리뷰 개요
    private String reviewGoodContent; // 장점 리뷰 내용
    private String reviewBadContent; // 단점 리뷰 내용
    private int trafficPoint; // 교통/접근성 점수
    private int conveniencePoint; // 편의성 점수
    private int noisePoint; // 소음 점수
    private int safetyPoint; // 치안 점수
    private String createdBy; // 작성자
    private LocalDateTime createdAt; // 작성시간

}
