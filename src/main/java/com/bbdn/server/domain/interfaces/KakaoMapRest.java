package com.bbdn.server.domain.interfaces;

import com.bbdn.server.domain.interfaces.vo.kakao.KakaoPlaceVO;
import com.bbdn.server.domain.interfaces.dto.QueryParameterDTO;

public interface KakaoMapRest {
  KakaoPlaceVO searchPlaceByKeyword(QueryParameterDTO queryParameterDto);
}
