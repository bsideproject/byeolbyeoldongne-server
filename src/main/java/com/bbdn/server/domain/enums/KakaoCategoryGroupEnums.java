package com.bbdn.server.domain.enums;

import lombok.Getter;

@Getter
public enum KakaoCategoryGroupEnums {

    // 몰세권
    MART("대형마트", "MART", "MT1"),

    // 편세권
    CONVENIENCE_STORE("편의점", "CONVENIENCE_STORE", "CS2"),

    // 카세권
    CAFE("카페", "CAFE", "CE7"),

    // 의세권
    HOSPITAL("병원", "MEDICAL", "HP8"),
    PHARMACY("약국", "MEDICAL", "PM9"),

    // 숲세권
    FOREST("숲", "FOREST", ""),
    PARK("공원", "FOREST", ""),

    // 운세권
    GYM("헬스장", "GYM", ""),
    PILATES("필라테스", "GYM", ""),

    // 햄세권
    BURGERKING("버거킹", "HAMBURGER", ""),
    MCDONALD("맥도날드", "HAMBURGER", ""),
    LOTTELIA("롯데리아", "HAMBURGER", ""),
    MOMSTOUCH("맘스터치", "HAMBURGER", "");

//    PS3("어린이집,유치원"),
//    SC4("학교"),
//    AC5("학원"),
//    PK6("주차장"),
//    OL7("주유소,충전소"),
//    SW8("지하철역"),
//    BK9("은행"),
//    CT1("문화시설"),
//    AG2("중개업소"),
//    PO3("공공기관"),
//    AT4("관광명소"),
//    AD5("숙박"),
//    FD6("음식점"),

    private final String name;
    private final String bbdnGroupCode;
    private final String kakaoGroupCode;

    KakaoCategoryGroupEnums(String name, String bbdnGroupCode, String kakaoGroupCode) {
        this.name = name;
        this.bbdnGroupCode = bbdnGroupCode;
        this.kakaoGroupCode = kakaoGroupCode;
    }
}
