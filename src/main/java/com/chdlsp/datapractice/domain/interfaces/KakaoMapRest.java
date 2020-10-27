package com.chdlsp.datapractice.domain.interfaces;

import com.chdlsp.datapractice.domain.interfaces.dto.QueryParameterDTO;
import com.chdlsp.datapractice.domain.interfaces.vo.KakaoPlaceVO;

public interface KakaoMapRest {
  KakaoPlaceVO searchPlaceByKeyword(QueryParameterDTO queryParameterDto);
}
