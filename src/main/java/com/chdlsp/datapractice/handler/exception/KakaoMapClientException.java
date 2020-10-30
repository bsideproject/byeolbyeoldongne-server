package com.chdlsp.datapractice.handler.exception;

import com.chdlsp.datapractice.domain.enums.ErrorCodeEnums;

public class KakaoMapClientException extends RuntimeException {
  public KakaoMapClientException(String message) {
    super(message);
  }
}
