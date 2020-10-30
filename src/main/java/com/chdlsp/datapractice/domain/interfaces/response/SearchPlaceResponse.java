package com.chdlsp.datapractice.domain.interfaces.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SearchPlaceResponse {

    private String addressName;
    private String roadAddress;
    private String zoneNo;
    private String mainBuildingNo;

}
