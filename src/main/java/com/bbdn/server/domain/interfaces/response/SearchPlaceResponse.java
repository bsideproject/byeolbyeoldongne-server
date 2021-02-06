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

    private String id;
    private String addressName;
    private String roadAddress;

    private Double x;
    private Double y;
}
