package com.chdlsp.datapractice.handler.exception;

public class BadRequestException extends KakaoMapClientException{

  public BadRequestException(String message) {
    super(message);
  }
}
