package com.bbdn.server.handler.exception;

import com.bbdn.server.domain.enums.ErrorCodeEnums;

public class BadSearchRequestException extends RuntimeException {

  private ErrorCodeEnums errorCodeEnums;
  private String message;

  public BadSearchRequestException(ErrorCodeEnums errorCodeEnums, String message) {
    this.errorCodeEnums = errorCodeEnums;
    this.message = message;
  }

}
