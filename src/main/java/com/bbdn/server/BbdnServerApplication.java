package com.bbdn.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BbdnServerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BbdnServerApplication.class, args);
	}

	@Bean
	public RestTemplateCustomizer restTemplateCustomizer() {
		return restTemplate -> {
			// PSA - portable service abstraction
			// HttpComponentsClientHttpRequestFactory 선언 시 Apache 의 httpClient 를 사용하게 됨
			restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		};
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BbdnServerApplication.class);
	}
}
