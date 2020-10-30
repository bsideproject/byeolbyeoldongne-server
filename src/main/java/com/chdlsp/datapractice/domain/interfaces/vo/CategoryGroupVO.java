package com.chdlsp.datapractice.domain.interfaces.vo;

import com.chdlsp.datapractice.domain.enums.KakaoCategoryGroupEnums;
import com.chdlsp.datapractice.domain.enums.PlaceServiceTypeEnums;
import lombok.Builder;
import lombok.Getter;

import java.beans.ConstructorProperties;

@Getter
public class CategoryGroupVO {
    private final PlaceServiceTypeEnums placeServiceTypeEnums;
    private final String code;
    private final String name;

    @Builder
    @ConstructorProperties({"placeServiceType", "code", "name"})
    public CategoryGroupVO(PlaceServiceTypeEnums placeServiceTypeEnums, String code, String name) {
        this.placeServiceTypeEnums = placeServiceTypeEnums;
        this.code = code;
        this.name = name;
    }
}
