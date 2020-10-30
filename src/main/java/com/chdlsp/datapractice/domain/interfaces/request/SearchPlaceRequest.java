package com.chdlsp.datapractice.domain.interfaces.request;

import com.chdlsp.datapractice.domain.enums.KakaoCategoryGroupEnums;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SearchPlaceRequest {

    private String query;
    private KakaoCategoryGroupEnums kakaoCategoryGroupEnums;
    private Double x;
    private Double y;
    private Integer radius;
    private String rect;
    private Integer page;
    private Integer size;

}
