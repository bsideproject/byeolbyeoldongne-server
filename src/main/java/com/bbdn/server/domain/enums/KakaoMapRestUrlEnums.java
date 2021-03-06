package com.bbdn.server.domain.enums;

import com.bbdn.server.application.common.client.AdapterRestUrl;
import org.springframework.http.HttpMethod;

public enum KakaoMapRestUrlEnums implements AdapterRestUrl {
    RETRIEVE_KEYWORD_BY_V2("v2/local/search/keyword.json", HttpMethod.GET),
    RETRIEVE_PLACE_BY_V2("/v2/local/search/address.json", HttpMethod.GET);

    private final String url;
    private final HttpMethod httpMethod;

    KakaoMapRestUrlEnums(String url, HttpMethod httpMethod) {
        this.url = url;
        this.httpMethod = httpMethod;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public HttpMethod getMethod() {
        return httpMethod;
    }
}
