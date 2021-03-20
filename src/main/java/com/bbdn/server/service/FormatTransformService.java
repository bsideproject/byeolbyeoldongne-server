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

    private final KakaoPlaceService kakaoPlaceService;

    public FormatTransformService(KakaoPlaceService kakaoPlaceService) {
        this.kakaoPlaceService = kakaoPlaceService;
    }

    public List<SearchPlaceResponse> retrieveLocationListByQuery(SearchPlaceResultDTO searchPlaceResultDTO) {

        List<SearchPlaceResponse> responseList = new ArrayList<>();

        List<Place> places = searchPlaceResultDTO.getPlaces();

        places.forEach(place -> {
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
                    .lng(x)
                    .lat(y)
                    .build());
        });

        log.info("responseList: {}", responseList.toString());
        return responseList;
    }

    public SearchPlaceResponse retrieveLocationListByPosition(SearchPlaceResultDTO searchPlaceResultDTO) {

        Place place = searchPlaceResultDTO.getPlaces().get(0);

        String id = place.getId();
        String addressName = place.getAddressName();
        String roadAddressName = place.getRoadAddressName();
        String placeName = place.getPlaceName();
        Double x = place.getX();
        Double y = place.getY();

        return SearchPlaceResponse.builder()
                .placeId(id)
                .addressName(addressName)
                .roadAddress(roadAddressName)
                .placeName(placeName)
                .lng(x)
                .lat(y)
                .build();
    }
}
