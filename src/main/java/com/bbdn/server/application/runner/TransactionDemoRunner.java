package com.bbdn.server.application.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientCodecCustomizer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class TransactionDemoRunner implements ApplicationRunner {

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @Autowired
    WebClient.Builder webClientBuilder;

    @Override
    public void run(ApplicationArguments args) {

        // RestTemplate 은 동기 방식으로 수행 됨 (Blocking IO 기반)
        RestTemplate restTemplate = restTemplateBuilder.build();

        StopWatch stopWatchForRest = new StopWatch();
        stopWatchForRest.start();

        String forObjectSuccess = restTemplate.getForObject("http://localhost/web/success", String.class);
        log.info(forObjectSuccess);

        String forObjectFailure = restTemplate.getForObject("http://localhost/web/failure", String.class);
        log.info(forObjectFailure);

        stopWatchForRest.stop();
        log.info(stopWatchForRest.prettyPrint());


        log.info("==============================================");
        log.info("WebClient Test ... ===========================");

        // WebClient 은 비동기 방식으로 수행 됨 (NonBlocking IO 기반)
        WebClient webClient = webClientBuilder
                .baseUrl("http://localhost/web")
                .build();

        StopWatch stopWatchForWeb = new StopWatch();
        stopWatchForWeb.start();

        // 아래 코드는 실행 해도 전혀 아무일도 실행되지 않음...
        // Mono, Flux 등에 대해 학습이 필요
        // Mono => stream 을 subscribe 하기 전 까지는 물이 흐르지 않는다.
        Mono<String> webMonoSuccess = webClient.get().uri("/success")
                .retrieve()
                .bodyToMono(String.class);

        // 물이 흐른다 !
        webMonoSuccess.subscribe(s -> {
           log.info(s);

           if(stopWatchForWeb.isRunning()) {
               stopWatchForWeb.stop();
           }
            log.info(stopWatchForRest.prettyPrint());
        });

        Mono<String> webMonoFailure = webClient.get().uri("/failure")
                .retrieve()
                .bodyToMono(String.class);

        webMonoFailure.subscribe(s -> {
            log.info(s);

            if(stopWatchForWeb.isRunning()) {
                stopWatchForWeb.stop();
            }
            log.info(stopWatchForRest.prettyPrint());
        });
    }
}
