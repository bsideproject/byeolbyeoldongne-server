package com.bbdn.server.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Component
public class GoogleClientConfig {

    RestTemplateBuilder restTemplateBuilder;

    @Bean
    public RestTemplate localTestTemplate() {
        return restTemplateBuilder.rootUri("http://localhost:8899")
                .additionalInterceptors(new RestTemplateClientHttpRequestInterceptor())
                .errorHandler(new RestTemplateErrorHandler())
                .setConnectTimeout(Duration.ofMinutes(3))
                .build();
    }

    @Bean
    public RestTemplate GoogleMapsApiTemplate() {
        return restTemplateBuilder.rootUri("http://xxxx")
                .additionalInterceptors(new RestTemplateClientHttpRequestInterceptor())
                .errorHandler(new RestTemplateErrorHandler())
                .setConnectTimeout(Duration.ofMinutes(3))
                .build();
    }
}
