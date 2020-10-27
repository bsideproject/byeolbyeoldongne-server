package com.chdlsp.datapractice.service;

import com.chdlsp.datapractice.domain.interfaces.KakaoMapClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KakaoPlaceService {

    private final KakaoMapClient kakaoMapClient;

    protected KakaoPlaceService(@Value("${kakao.map.api-url}") String serverUrl,
                                      @Value("${kakao.admin-key}") String adminKey) {
        this.kakaoMapClient = new KakaoMapClient(serverUrl, adminKey);
    }

}
