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
            String placeName = place.getPlaceName();
            Double x = place.getX();
            Double y = place.getY();

            responseList.add(SearchPlaceResponse.builder()
                    .placeId(id)
                    .addressName(addressName)
                    .roadAddress(roadAddressName)
                    .placeName(placeName)
                    .x(x)
                    .y(y)
                    .build());
        }

        log.info("retrieveLocationListByQuery responseList : " + responseList);

        return responseList;
    }

    public SearchPlaceResponse retrieveLocationListByPosition(SearchPlaceResultDTO searchPlaceResultDTO) {

        String id = searchPlaceResultDTO.getPlaces().get(0).getId();
        String addressName = searchPlaceResultDTO.getPlaces().get(0).getAddressName();
        String roadAddressName = searchPlaceResultDTO.getPlaces().get(0).getRoadAddressName();
        String placeName = searchPlaceResultDTO.getPlaces().get(0).getPlaceName();
        Double x = searchPlaceResultDTO.getPlaces().get(0).getX();
        Double y = searchPlaceResultDTO.getPlaces().get(0).getY();

        return SearchPlaceResponse.builder()
                .placeId(id)
                .addressName(addressName)
                .roadAddress(roadAddressName)
                .placeName(placeName)
                .x(x)
                .y(y)
                .build();
    }
}
