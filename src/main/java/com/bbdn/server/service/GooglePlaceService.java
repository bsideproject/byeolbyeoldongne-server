package com.bbdn.server.service;

import com.bbdn.server.application.client.GoogleClient;
import com.bbdn.server.domain.interfaces.request.SearchKakaoPlaceRequest;
import com.bbdn.server.domain.interfaces.response.SearchPlaceResponse;
import com.bbdn.server.domain.interfaces.vo.google.GooglePlaceVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class GooglePlaceService {

    private GoogleClient googleClient;

    public GooglePlaceService(GoogleClient googleClient) {
        this.googleClient = googleClient;
    }

    public List<SearchPlaceResponse> searchPlaceByQueryParameter(SearchKakaoPlaceRequest searchKakaoPlaceRequest) {

        List<GooglePlaceVO> googlePlaceVOList = googleClient.getGoogleGeocodeApi(searchKakaoPlaceRequest);
        List<SearchPlaceResponse> searchPlaceResponseList = new ArrayList<>();

        log.info("googlePlaceVOList : " + googlePlaceVOList);
        for(GooglePlaceVO googlePlaceVO : googlePlaceVOList) {
            SearchPlaceResponse searchPlaceResponse = SearchPlaceResponse.builder()
                    .addressName(googlePlaceVO.getFormatted_address())
                    .placeId(googlePlaceVO.getPlace_id())
                    .lng(googlePlaceVO.getGeometry().location.lat)
                    .lat(googlePlaceVO.getGeometry().location.lng)
                    .roadAddress(googlePlaceVO.getFormatted_address())
                    .build();

            searchPlaceResponseList.add(searchPlaceResponse);
        }
        return searchPlaceResponseList;
    }
}
