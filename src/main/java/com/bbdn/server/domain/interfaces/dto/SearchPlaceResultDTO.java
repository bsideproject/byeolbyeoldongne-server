package com.bbdn.server.domain.interfaces.dto;


import com.bbdn.server.domain.interfaces.spec.Place;
import com.bbdn.server.domain.interfaces.vo.PaginationVO;
import com.bbdn.server.domain.interfaces.vo.RegionInfoVO;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.beans.ConstructorProperties;
import java.util.List;

@Getter
@ToString
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
