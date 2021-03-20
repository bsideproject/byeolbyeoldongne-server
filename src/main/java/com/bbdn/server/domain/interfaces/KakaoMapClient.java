package com.bbdn.server.domain.interfaces;

import com.bbdn.server.application.common.SpringWebRestClient;
import com.bbdn.server.application.common.client.RequestBuilder;
import com.bbdn.server.domain.enums.KakaoMapRestUrlEnums;
import com.bbdn.server.domain.interfaces.vo.kakao.KakaoPlaceVO;
import com.bbdn.server.handler.exception.KakaoMapClientException;
import com.bbdn.server.domain.interfaces.dto.QueryParameterDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Objects;

@Slf4j
public class KakaoMapClient implements KakaoMapRest {

    private final String adminKey;
    private final SpringWebRestClient webRestClient;

    public KakaoMapClient(String url, String adminKey) {
        this.adminKey = adminKey;
        this.webRestClient = new SpringWebRestClient(url);
    }

    @Override
    public KakaoPlaceVO searchPlaceByKeyword(QueryParameterDTO queryParameterDTO) {
        try {
            log.info("searchPlaceByKeyword: " + webRestClient.sendAndReceive(this.createRequestBuilder(queryParameterDTO)).toString());
            return webRestClient.sendAndReceive(this.createRequestBuilder(queryParameterDTO));
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new KakaoMapClientException(e.getMessage());
        }
    }

    @Override
    public KakaoPlaceVO searchPlaceByPosition(QueryParameterDTO queryParameterDTO) {
        try {
            log.info("searchPlaceByPosition: " + webRestClient.sendAndReceive(this.createRequestBuilder(queryParameterDTO)).toString());
            return webRestClient.sendAndReceive(this.createRequestBuilder(queryParameterDTO));
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new KakaoMapClientException(e.getMessage());
        }
    }

    @Deprecated
    public KakaoPlaceVO searchPlaceByCategoryGroup(QueryParameterDTO queryParameterDTO) {
        try {
            log.info("searchPlaceByCategoryGroup: " + webRestClient.sendAndReceive(this.createRequestBuilder(queryParameterDTO)).toString());
            return webRestClient.sendAndReceive(this.createRequestBuilder(queryParameterDTO));
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new KakaoMapClientException(e.getMessage());
        }
    }

    private RequestBuilder createRequestBuilder(QueryParameterDTO queryParameterDTO) {

        RequestBuilder requestBuilder = new RequestBuilder(queryParameterDTO.getKakaoMapRestUrlEnums());
        requestBuilder.addHeader("Authorization", this.adminKey);
        requestBuilder.addQueryParam("query", queryParameterDTO.getQuery());
        requestBuilder.setResponseType(KakaoPlaceVO.class);

        if (!StringUtils.isEmpty(queryParameterDTO.getKakaoCategoryGroupEnums())) {
            requestBuilder.addQueryParam("category_group_code", queryParameterDTO.getKakaoCategoryGroupEnums().getKakaoGroupCode());
        }
        if (!Objects.isNull(queryParameterDTO.getX())) {
            requestBuilder.addQueryParam("x", queryParameterDTO.getX().toString());
        }
        if (!Objects.isNull(queryParameterDTO.getY())) {
            requestBuilder.addQueryParam("y", queryParameterDTO.getY().toString());
        }
        if (!Objects.isNull(queryParameterDTO.getRadius())) {
            requestBuilder.addQueryParam("radius", queryParameterDTO.getRadius());
        }
        if (!StringUtils.isEmpty(queryParameterDTO.getRect())) {
            requestBuilder.addQueryParam("rect", queryParameterDTO.getRect());
        }
        if (!Objects.isNull(queryParameterDTO.getPage())) {
            requestBuilder.addQueryParam("page", queryParameterDTO.getPage());
        }
        if (!Objects.isNull(queryParameterDTO.getSize())) {
            requestBuilder.addQueryParam("size", queryParameterDTO.getSize());
        }
        return requestBuilder;
    }
}
