package com.bbdn.server.domain.interfaces.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PlaceReviewModifyRequest {

    // 카카오-장소 API response

    private String placeId; // 장소ID
    private String addressName; // 전체도로명주소
    private String roadAddress; // 도로명주소
    private Long x; // x좌표
    private Long y; // y좌표

    private String reviewSequence; // 리뷰 일련번호
    private String reviewMainContent; // 리뷰 개요
    private String reviewGoodContent; // 장점 리뷰 내용
    private String reviewBadContent; // 단점 리뷰 내용
    private int trafficPoint; // 교통/접근성 점수
    private int conveniencePoint; // 편의성 점수
    private int noisePoint; // 소음 점수
    private int safetyPoint; // 치안 점수

    @Email
    private String email; // 작성자

}
