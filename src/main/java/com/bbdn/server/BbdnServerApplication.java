package com.bbdn.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BbdnServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BbdnServerApplication.class, args);
	}

	@Bean
	public RestTemplateCustomizer restTemplateCustomizer() {
		return new RestTemplateCustomizer() {
			@Override
			public void customize(RestTemplate restTemplate) {
				// PSA - portable service abstraction
				// HttpComponentsClientHttpRequestFactory 선언 시 Apache 의 httpClient 를 사용하게 됨
				restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
			}
		};
	}
}
