package com.bbdn.server.service;

import com.bbdn.server.application.client.GoogleClient;
import com.bbdn.server.domain.interfaces.request.SearchPlaceRequest;
import com.bbdn.server.domain.interfaces.response.bbdn.SearchPlaceResponse;
import com.bbdn.server.domain.interfaces.vo.google.GoogleGeoCodeVO;
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

    public List<SearchPlaceResponse> searchPlaceByQueryParameter(SearchPlaceRequest searchPlaceRequest) {

        List<GooglePlaceVO> googlePlaceVOList = googleClient.getGoogleGeocodeApi(searchPlaceRequest);
        List<SearchPlaceResponse> searchPlaceResponseList = new ArrayList<>();

        log.info("googlePlaceVOList : " + googlePlaceVOList);
        for(GooglePlaceVO googlePlaceVO : googlePlaceVOList) {
            SearchPlaceResponse searchPlaceResponse = SearchPlaceResponse.builder()
                    .addressName(googlePlaceVO.getFormatted_address())
                    .id(googlePlaceVO.getPlace_id())
                    .x(googlePlaceVO.getGeometry().location.lat)
                    .y(googlePlaceVO.getGeometry().location.lng)
                    .roadAddress(googlePlaceVO.getFormatted_address())
                    .build();

            searchPlaceResponseList.add(searchPlaceResponse);
        }
        return searchPlaceResponseList;
    }
}
