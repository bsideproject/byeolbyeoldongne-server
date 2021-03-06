package com.bbdn.server.domain.interfaces.request;

import com.bbdn.server.domain.enums.KakaoCategoryGroupEnums;
import com.bbdn.server.domain.enums.KakaoMapRestUrlEnums;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SearchKakaoPlaceRequest {

    private String query; // /v2/local/search/address
    private String addressName; // /v2/local/search/keyword
    private KakaoCategoryGroupEnums kakaoCategoryGroupEnums;

    private Double x;
    private Double y;
    private Integer radius;
    private String rect;
    private Integer page;
    private Integer size;

    private KakaoMapRestUrlEnums kakaoMapRestUrlEnums;

}
