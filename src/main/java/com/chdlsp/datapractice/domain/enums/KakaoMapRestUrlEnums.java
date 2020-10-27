package com.chdlsp.datapractice.domain.enums;

import com.chdlsp.datapractice.application.common.client.AdapterRestUrl;
import org.springframework.http.HttpMethod;

public enum KakaoMapRestUrlEnums implements AdapterRestUrl {
    RETRIEVE_PLACE_BY_KEYWORD_V2("v2/local/search/keyword.json", HttpMethod.GET);


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
