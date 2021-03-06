package com.bbdn.server.application.controller;

import com.bbdn.server.domain.interfaces.dto.SearchPlaceResultDTO;
import com.bbdn.server.domain.interfaces.request.SearchKakaoPlaceRequest;
import com.bbdn.server.domain.interfaces.response.SearchPlaceResponse;
import com.bbdn.server.service.FormatTransformService;
import com.bbdn.server.service.GooglePlaceService;
import com.bbdn.server.service.KakaoPlaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/location")
public class LocationController {

    final KakaoPlaceService kakaoPlaceService;
    final GooglePlaceService googlePlaceService;
    final FormatTransformService formatTransformService;

    public LocationController(KakaoPlaceService kakaoPlaceService, GooglePlaceService googlePlaceService, FormatTransformService formatTransformService) {
        this.kakaoPlaceService = kakaoPlaceService;
        this.googlePlaceService = googlePlaceService;
        this.formatTransformService = formatTransformService;
    }

    @GetMapping("/list/google")
    public ResponseEntity getGoogleLoadListByQuery(@RequestParam("query") String query) {

        log.info("getLoadListByQuery query : " + query);

        SearchKakaoPlaceRequest searchKakaoPlaceRequest = SearchKakaoPlaceRequest
                .builder()
                .query(query)
                .build();

        List<SearchPlaceResponse> searchPlaceResponseList = googlePlaceService.searchPlaceByQueryParameter(searchKakaoPlaceRequest);
        return ResponseEntity.ok(searchPlaceResponseList);
    }

    @GetMapping("/list/place")
    public ResponseEntity getPlaceListByQuery(@RequestParam("query") String query) {

        log.info("getPlaceListByQuery query : " + query);

        SearchKakaoPlaceRequest searchKakaoPlaceRequest = SearchKakaoPlaceRequest.builder().query(query).build();
        SearchPlaceResultDTO searchPlaceResultDTO = kakaoPlaceService.searchPlaceByQueryParameter(searchKakaoPlaceRequest);

        log.info("getPlaceListByQuery searchPlaceResultDTO : " + searchPlaceResultDTO.toString());

        List<SearchPlaceResponse> responseEntity = formatTransformService.retrieveLocationListByQuery(searchPlaceResultDTO);

        return ResponseEntity.ok(responseEntity);
    }

    @GetMapping("/list/keyword")
    public ResponseEntity getKeywordListByAddressName(@RequestParam("addressName") String addressName) {

        log.info("getKeywordListByAddressName addressName : " + addressName);

        SearchKakaoPlaceRequest searchKakaoPlaceRequest = SearchKakaoPlaceRequest.builder().query(addressName).build();
        SearchPlaceResultDTO searchPlaceResultDTO = kakaoPlaceService.searchKeywordByQueryParameter(searchKakaoPlaceRequest);

        log.info("getLoadListByQuery searchPlaceResultDTO : " + searchPlaceResultDTO.toString());

        return ResponseEntity.ok(null);
    }
}
