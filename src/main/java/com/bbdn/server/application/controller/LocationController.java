package com.bbdn.server.application.controller;

import com.bbdn.server.domain.interfaces.dto.SearchPlaceResultDTO;
import com.bbdn.server.domain.interfaces.request.SearchKakaoPlaceRequest;
import com.bbdn.server.domain.interfaces.response.LocationLineByAddressNameResponse;
import com.bbdn.server.domain.interfaces.response.SearchPlaceResponse;
import com.bbdn.server.domain.interfaces.vo.AddressLocationVO;
import com.bbdn.server.service.FormatTransformService;
import com.bbdn.server.service.GooglePlaceService;
import com.bbdn.server.service.KakaoPlaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @Deprecated
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

        Objects.requireNonNull(query, "@RequestParam query 질의어는 필수 입니다.");

        SearchKakaoPlaceRequest searchKakaoPlaceRequest = SearchKakaoPlaceRequest.builder().query(query).build();
        SearchPlaceResultDTO searchPlaceResultDTO = kakaoPlaceService.searchPlaceByQueryParameter(searchKakaoPlaceRequest);
        List<SearchPlaceResponse> searchPlaceResponseList = formatTransformService.retrieveLocationListByQuery(searchPlaceResultDTO);

        return ResponseEntity.ok(searchPlaceResponseList);
    }

    @GetMapping("/position")
    public ResponseEntity getPlaceListByPosition(@RequestParam("lat") double y,
                                                 @RequestParam("lng") double x) {

        SearchKakaoPlaceRequest searchKakaoPlaceRequest = SearchKakaoPlaceRequest.builder()
                .x(x)
                .y(y)
                .build();

        // 좌표를 이용해 Kakao API 조회
        String roadName = kakaoPlaceService.searchPlaceByPositionParameter(searchKakaoPlaceRequest);

        // 도로명주소를 이용한 조회
        searchKakaoPlaceRequest.setQuery(roadName);
        SearchPlaceResultDTO searchPlaceResultDTO = kakaoPlaceService.searchPlaceByQueryParameter(searchKakaoPlaceRequest);
        log.info("getPlaceListByPosition searchPlaceResultDTO: " + searchPlaceResultDTO.toString());

        // response setting
        LocationLineByAddressNameResponse locationLineByAddressNameResponse = new LocationLineByAddressNameResponse();

        SearchPlaceResponse searchPlaceResponse = formatTransformService.retrieveLocationListByPosition(searchPlaceResultDTO);
        List<AddressLocationVO> addressLocationVOList = kakaoPlaceService.searchKeywordByQueryParameter(searchKakaoPlaceRequest);

        if(addressLocationVOList.size() > 1) {
            // roadAddressName 기준으로 sort
            Collections.sort(addressLocationVOList);
            AddressLocationVO firstAddressLocation = addressLocationVOList.get(0);
            AddressLocationVO lastAddressLocation = addressLocationVOList.get(addressLocationVOList.size()-1);

            locationLineByAddressNameResponse.setAddressName(roadName);
            locationLineByAddressNameResponse.setStartLocation(firstAddressLocation);
            locationLineByAddressNameResponse.setEndLocation(lastAddressLocation);
        }

        return ResponseEntity.ok(searchPlaceResponse);
    }

    @GetMapping("/line")
    public ResponseEntity getFromToLocationByAddressName(@RequestParam("addressName") String addressName) {

        LocationLineByAddressNameResponse locationLineByAddressNameResponse = new LocationLineByAddressNameResponse();

        SearchKakaoPlaceRequest searchKakaoPlaceRequest = SearchKakaoPlaceRequest.builder().query(addressName).build();
        List<AddressLocationVO> addressLocationVOList = kakaoPlaceService.searchKeywordByQueryParameter(searchKakaoPlaceRequest);

        if(addressLocationVOList.size() > 1) {
            // roadAddressName 기준으로 sort
            Collections.sort(addressLocationVOList);
            AddressLocationVO firstAddressLocation = addressLocationVOList.get(0);
            AddressLocationVO lastAddressLocation = addressLocationVOList.get(addressLocationVOList.size()-1);

            locationLineByAddressNameResponse.setAddressName(addressName);
            locationLineByAddressNameResponse.setStartLocation(firstAddressLocation);
            locationLineByAddressNameResponse.setEndLocation(lastAddressLocation);
        }

        return ResponseEntity.ok(locationLineByAddressNameResponse);
    }

}
