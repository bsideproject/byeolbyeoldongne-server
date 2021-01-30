package com.bbdn.server.service;

import com.bbdn.server.application.client.GoogleClient;
import com.bbdn.server.domain.interfaces.dto.SearchPlaceResultDTO;
import com.bbdn.server.domain.interfaces.request.SearchPlaceRequest;
import com.bbdn.server.domain.interfaces.response.bbdn.SearchPlaceResponse;
import com.bbdn.server.domain.interfaces.vo.google.GeocodeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GooglePlaceService {

    private GoogleClient googleClient;

    public GooglePlaceService(GoogleClient googleClient) {
        this.googleClient = googleClient;
    }

    public SearchPlaceResponse searchPlaceByQueryParameter(SearchPlaceRequest searchPlaceRequest) {

        log.info("searchPlaceRequest : "  +  searchPlaceRequest);

        GeocodeResponse geocodeResponse = googleClient.getGoogleGeocodeApi(searchPlaceRequest);

        SearchPlaceResponse searchPlaceResponse = SearchPlaceResponse.builder()
                .addressName(geocodeResponse.getFormattedAddress())
                .id(geocodeResponse.getFormattedAddress())
                .x(geocodeResponse.getLatitude())
                .y(geocodeResponse.getLongitude())
                .roadAddress(geocodeResponse.getFormattedAddress())
                .build();

        return searchPlaceResponse;
    }
}
