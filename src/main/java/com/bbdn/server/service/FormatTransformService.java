package com.bbdn.server.service;

import com.bbdn.server.domain.interfaces.dto.SearchPlaceResultDTO;
import com.bbdn.server.domain.interfaces.response.SearchPlaceResponse;
import com.bbdn.server.domain.interfaces.spec.Place;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class FormatTransformService {

    public List<SearchPlaceResponse> retrieveLocationListByQuery(SearchPlaceResultDTO searchPlaceResultDTO) {

        List<SearchPlaceResponse> responseList = new ArrayList<>();

        List<Place> places = searchPlaceResultDTO.getPlaces();

        for (Place place : places) {
            String id = place.getId();
            String addressName = place.getAddressName();
            String roadAddressName = place.getRoadAddressName();
            Double x = place.getX();
            Double y = place.getY();

            responseList.add(SearchPlaceResponse.builder()
                    .id(id)
                    .addressName(addressName)
                    .roadAddress(roadAddressName)
                    .x(x)
                    .y(y)
                    .build());
        }

        log.info("retrieveLocationListByQuery responseList : " + responseList);

        return responseList;
    }
}
