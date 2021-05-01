package com.bbdn.server.application.common;

import com.bbdn.server.application.common.client.AdapterRestClient;
import com.bbdn.server.application.common.client.RequestBuilder;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;

@Slf4j
public class SpringWebRestClient implements AdapterRestClient {
    private RestTemplate restTemplate;
    private String serverHost;
    private String jSessionId = null;

    public SpringWebRestClient(String serverHost) {
        if (!serverHost.endsWith("/")) {
            serverHost = serverHost + "/";
        }
        this.serverHost = serverHost;

        this.restTemplate = new RestTemplate();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.OBJECT_AND_NON_CONCRETE);
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        HttpMessageConverter<String> stringHttpMessageConverter = new StringHttpMessageConverter(
                StandardCharsets.UTF_8);
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);

        restTemplate.getMessageConverters().add(jackson2HttpMessageConverter);
        restTemplate.getMessageConverters().add(stringHttpMessageConverter);
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                String message = getStringFromInputStream(response.getBody());
                log.warn("[ResTemplateError] status = [{}], body = [{}]", response.getStatusCode(),
                        message);
                super.handleError(response);
            }
        });
    }

    // convert InputStream to String
    private String getStringFromInputStream(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            while (((line = br.readLine()) != null)) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    @Override
    public <T> T sendAndReceive(RequestBuilder requestBuilder) {
        return (T) sendAndReceiveEntity(requestBuilder).getBody();
    }

    @Override
    public <T> ResponseEntity<T> sendAndReceiveEntity(RequestBuilder requestBuilder) {
//
        String url = requestBuilder.getUrl();
        String httpMethod = requestBuilder.getMethod();

        Object request = requestBuilder.getRequestBody();
        Class clazz = requestBuilder.getResponseType();

        HttpHeaders headers = requestBuilder.getHeaders();
        Map<String, String> pathParams = requestBuilder.getPathParams();
        MultiValueMap<String, String> queryParams = requestBuilder.getQueryParams();

        HttpEntity<Object> requestEntity = new HttpEntity<>(request, headers);
        String httpUrl = serverHost + url;
        if (pathParams != null) {
            Set<String> keys = pathParams.keySet();
            for (String key : keys) {
                httpUrl = httpUrl.replace("{" + key + "}", pathParams.get(key));
            }
        }
        if (queryParams != null) {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(httpUrl);
            builder.queryParams(queryParams);
            httpUrl = builder.build().toUriString();
        }

        log.error(String.format("[ResTemplateExchange] to = [%s], method = [%s]", httpUrl,
                HttpMethod.valueOf(httpMethod)));
        log.error(String
                .format("[ResTemplateExchange] headers = %s, body = [%s] ", requestEntity.getHeaders(),
                        requestEntity.getBody()));
        ResponseEntity responseEntity = restTemplate.exchange(httpUrl, HttpMethod.valueOf(httpMethod), requestEntity, clazz);
        HttpHeaders responseHeaders = responseEntity.getHeaders();
        requestBuilder.setRespHeaders(responseHeaders);
        log.error("[ResTemplateResult] status = {{}}, body = {{}}", responseEntity.getStatusCode(),
                responseEntity.getBody());
        return responseEntity;
    }
}


