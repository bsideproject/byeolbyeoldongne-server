package com.chdlsp.datapractice.application.controller;

import com.chdlsp.datapractice.domain.interfaces.request.SearchPlaceRequest;
import com.chdlsp.datapractice.domain.interfaces.dto.SearchPlaceResultDTO;
import com.chdlsp.datapractice.service.KakaoPlaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/location")
public class LocationController {

    final KakaoPlaceService kakaoPlaceService;

    public LocationController(KakaoPlaceService kakaoPlaceService) {
        this.kakaoPlaceService = kakaoPlaceService;
    }

    @GetMapping("/list")
    public ResponseEntity getLoadListByQuery(@RequestParam String query) {

        log.debug("getLoadListByQuery query : " + query);

        SearchPlaceRequest searchPlaceRequest = SearchPlaceRequest.builder().query(query).build();
        SearchPlaceResultDTO searchPlaceResultDTO = kakaoPlaceService.searchPlaceByQueryParameter(searchPlaceRequest);

        return ResponseEntity.ok(searchPlaceResultDTO);
    }
}
