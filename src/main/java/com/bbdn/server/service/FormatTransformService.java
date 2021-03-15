package com.bbdn.server.service;

import com.bbdn.server.domain.enums.KakaoCategoryGroupEnums;
import com.bbdn.server.domain.interfaces.dto.SearchPlaceResultDTO;
import com.bbdn.server.domain.interfaces.request.SearchKakaoPlaceRequest;
import com.bbdn.server.domain.interfaces.response.LocationLineByAddressNameResponse;
import com.bbdn.server.domain.interfaces.response.SearchPlaceResponse;
import com.bbdn.server.domain.interfaces.spec.Place;
import com.bbdn.server.domain.interfaces.vo.AddressLocationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
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

            SearchKakaoPlaceRequest searchKakaoPlaceRequest = SearchKakaoPlaceRequest.builder()
                    .query(addressName)
                    .build();

            List<AddressLocationVO> addressLocationVOList =
                    kakaoPlaceService.searchKeywordByQueryParameter(searchKakaoPlaceRequest);

            log.info("retrieveLocationListByQuery addressLocationVOList: {}", addressLocationVOList.toString());
            if (addressLocationVOList.size() > 1) {
                // roadAddressName 기준으로 sort
                Collections.sort(addressLocationVOList);
                AddressLocationVO firstAddressLocation = addressLocationVOList.get(0);
                AddressLocationVO lastAddressLocation = addressLocationVOList.get(addressLocationVOList.size() - 1);

                LocationLineByAddressNameResponse locationLineByAddressNameResponse = LocationLineByAddressNameResponse.builder()
                        .startLocation(firstAddressLocation)
                        .endLocation(lastAddressLocation)
                        .addressName(roadAddressName)
                        .build();

                // 카테고리 그룹 조회
                searchKakaoPlaceRequest.setX(x);
                searchKakaoPlaceRequest.setY(y);
                searchKakaoPlaceRequest.setRadius(500);

                List<KakaoCategoryGroupEnums> kakaoCategoryGroupEnumsList = kakaoPlaceService.searchCategoryGroupListByParameter(searchKakaoPlaceRequest);

                responseList.add(SearchPlaceResponse.builder()
                        .placeId(id)
                        .addressName(addressName)
                        .roadAddress(roadAddressName)
                        .placeName(placeName)
                        .x(x)
                        .y(y)
                        .categoryGroupEnumsList(kakaoCategoryGroupEnumsList)
                        .locationLineByAddressNameResponse(locationLineByAddressNameResponse)
                        .build());
            }
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
                .x(x)
                .y(y)
                .build();
    }
}
