package com.bbdn.server.application.controller;

import com.bbdn.server.domain.interfaces.dto.SearchPlaceResultDTO;
import com.bbdn.server.domain.interfaces.request.SearchPlaceRequest;
import com.bbdn.server.domain.interfaces.response.SearchPlaceResponse;
import com.bbdn.server.service.FormatTransformService;
import com.bbdn.server.service.KakaoPlaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/location")
public class LocationController {

    final KakaoPlaceService kakaoPlaceService;
    final FormatTransformService formatTransformService;

    public LocationController(KakaoPlaceService kakaoPlaceService, FormatTransformService formatTransformService) {
        this.kakaoPlaceService = kakaoPlaceService;
        this.formatTransformService = formatTransformService;
    }

    @GetMapping("/list")
    public ResponseEntity getLoadListByQuery(@RequestParam String query) {

        log.info("getLoadListByQuery query : " + query);

        SearchPlaceRequest searchPlaceRequest = SearchPlaceRequest.builder().query(query).build();
        SearchPlaceResultDTO searchPlaceResultDTO = kakaoPlaceService.searchPlaceByQueryParameter(searchPlaceRequest);

        log.info("getLoadListByQuery searchPlaceResultDTO : " + searchPlaceResultDTO.toString());

        List<SearchPlaceResponse> responseEntity = formatTransformService.retrieveLocationListByQuery(searchPlaceResultDTO);

        return ResponseEntity.ok(responseEntity);
    }
}
