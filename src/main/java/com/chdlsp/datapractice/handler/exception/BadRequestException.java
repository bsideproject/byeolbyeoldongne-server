package com.chdlsp.datapractice.handler.exception;

import com.chdlsp.datapractice.domain.enums.ErrorCodeEnums;

public class BadRequestException extends KakaoMapClientException {

  public BadRequestException(String message) {
    super(message);
  }

}
