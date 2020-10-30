package com.chdlsp.datapractice.domain.interfaces.dto;


import com.chdlsp.datapractice.domain.interfaces.spec.Place;
import com.chdlsp.datapractice.domain.interfaces.vo.PaginationVO;
import com.chdlsp.datapractice.domain.interfaces.vo.RegionInfoVO;
import lombok.Builder;
import lombok.Getter;

import java.beans.ConstructorProperties;
import java.util.List;

@Getter
public class SearchPlaceResultDTO {
    private final PaginationVO pagination;
    private final List<Place> places;
    private final RegionInfoVO region;

    @Builder
    @ConstructorProperties({"pagination", "places", "region"})
    public SearchPlaceResultDTO(PaginationVO pagination,
                                List<Place> places, RegionInfoVO region) {
        this.pagination = pagination;
        this.places = places;
        this.region = region;
    }
}
