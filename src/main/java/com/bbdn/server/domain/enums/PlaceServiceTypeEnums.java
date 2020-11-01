package com.bbdn.server.domain.enums;

public enum PlaceServiceTypeEnums {
    KAKAO_MAP("카카오_지도_API"),
    PLACE("PLACE_서치_시스템");

    private final String description;

    PlaceServiceTypeEnums(String description) {
        this.description = description;
    }
}


