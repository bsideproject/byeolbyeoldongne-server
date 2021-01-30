package com.bbdn.server.application.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

public class GoogleClient {

    private final RestTemplate googleMapsApiTemplate;

    public GoogleClient(RestTemplate googleMapsApiTemplate) {
        this.googleMapsApiTemplate = googleMapsApiTemplate;
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
