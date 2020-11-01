package com.bbdn.server.handler.exception;

public class BadRequestException extends KakaoMapClientException {

  public BadRequestException(String message) {
    super(message);
  }

}
