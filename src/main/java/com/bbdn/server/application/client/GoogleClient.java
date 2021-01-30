package com.bbdn.server.application.client;

import com.bbdn.server.domain.interfaces.request.SearchPlaceRequest;
import com.bbdn.server.domain.interfaces.vo.google.GeocodeResponse;
import com.bbdn.server.domain.interfaces.vo.google.GeocodeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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

    public GeocodeResponse getGoogleGeocodeApi(SearchPlaceRequest searchPlaceRequest) {

        URI uri = UriComponentsBuilder.fromHttpUrl(googleMapApiUrl + "/maps/api/geocode/json")
                .queryParam("key", adminKey)
                .queryParam("address", searchPlaceRequest.getQuery())
                .build()
                .toUri();

        final ResponseEntity<String> responseEntity =
                googleMapsApiTemplate.getForEntity(uri, String.class);

        GeocodeResponse googleResponse = GeocodeDTO.parse(responseEntity.getBody());

        log.info("responseEntity : " + googleResponse);

        return googleResponse;
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
