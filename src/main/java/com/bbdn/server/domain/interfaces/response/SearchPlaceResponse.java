package com.bbdn.server.domain.interfaces.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SearchPlaceResponse {

    private String placeId;
    private String addressName;
    private String roadAddress;

    private String placeName;

    private Double x;
    private Double y;

    LocationLineByAddressNameResponse locationLineByAddressNameResponse;
}
