package com.chdlsp.datapractice.domain.interfaces;

import com.chdlsp.datapractice.application.common.SpringWebRestClient;
import com.chdlsp.datapractice.application.common.client.RequestBuilder;
import com.chdlsp.datapractice.domain.enums.KakaoMapRestUrlEnums;
import com.chdlsp.datapractice.domain.interfaces.dto.QueryParameterDTO;
import com.chdlsp.datapractice.domain.interfaces.vo.KakaoPlaceVO;
import com.chdlsp.datapractice.handler.exception.KakaoMapClientException;
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
            return webRestClient.sendAndReceive(this.createRequestBuilder(queryParameterDTO));
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new KakaoMapClientException(e.getMessage());
        }

    }

    private RequestBuilder createRequestBuilder(QueryParameterDTO queryParameterDTO) {
        System.out.println(queryParameterDTO.toString());
        RequestBuilder requestBuilder = new RequestBuilder(
                KakaoMapRestUrlEnums.RETRIEVE_PLACE_BY_KEYWORD_V2);
        requestBuilder.addHeader("Authorization", this.adminKey);
        requestBuilder.addQueryParam("query", queryParameterDTO.getQuery());
        requestBuilder.setResponseType(KakaoPlaceVO.class);

        if (!StringUtils.isEmpty(queryParameterDTO.getKakaoCategoryGroupEnums())) {
            requestBuilder
                    .addQueryParam("category_group_code", queryParameterDTO.getKakaoCategoryGroupEnums().getCode());
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
