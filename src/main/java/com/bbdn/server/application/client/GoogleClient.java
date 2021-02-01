package com.bbdn.server.application.client;

import com.bbdn.server.domain.interfaces.request.SearchPlaceRequest;
import com.bbdn.server.domain.interfaces.vo.google.GoogleGeoCodeVO;
import com.bbdn.server.domain.interfaces.vo.google.GooglePlaceVO;
import com.bbdn.server.handler.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class GoogleClient {

    private final RestTemplate googleMapsApiTemplate;

    final String googleMapApiUrl;
    final String adminKey;

    public GoogleClient(@Value("${google.map.api-url}") String googleMapApiUrl,
                        @Value("${google.admin-key}") String adminKey,
                        RestTemplate googleMapsApiTemplate) {
        this.googleMapsApiTemplate = googleMapsApiTemplate;
        this.googleMapApiUrl = googleMapApiUrl;
        this.adminKey = adminKey;
    }

    public List<GooglePlaceVO> getGoogleGeocodeApi(SearchPlaceRequest searchPlaceRequest) {

        URI uri = UriComponentsBuilder.fromHttpUrl(googleMapApiUrl + "/maps/api/geocode/json")
                .queryParam("key", adminKey)
                .queryParam("address", searchPlaceRequest.getQuery())
                .queryParam("language", "ko")
                .queryParam("region", "ko")

                .build()
                .toUri();

        final ResponseEntity<GoogleGeoCodeVO> responseEntity =
                googleMapsApiTemplate.getForEntity(uri, GoogleGeoCodeVO.class);

        if(responseEntity.getStatusCode().isError()) {
            throw new BadRequestException("Google API 조회 에러");
        }

        log.info("responseEntity.getBody() : " + responseEntity.getBody());
        return Objects.requireNonNull(responseEntity.getBody()).getResults();
    }

/*
    @PostMapping("/local-sign-up")
    public Member test(@RequestBody @Valid final SignUpRequest dto){
        final ResponseEntity<Member> responseEntity = localTestTemplate
                .postForEntity("/members", dto, Member.class);

        final Member member = responseEntity.getBody();
        return member;
    }
*/
}
