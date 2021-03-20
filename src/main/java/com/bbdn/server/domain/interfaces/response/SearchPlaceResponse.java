package com.bbdn.server.domain.interfaces.response;

import com.bbdn.server.domain.enums.KakaoCategoryGroupEnums;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SearchPlaceResponse {

    private String placeId;
    private String addressName;
    private String roadAddress;

    private String placeName;

    private Double lng;
    private Double lat;

    private Set<String> categoryGroupEnumsList;

    LocationLineByAddressNameResponse locationLineByAddressNameResponse;
}
