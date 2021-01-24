package com.bbdn.server.handler.exception;

public class IdNotFoundException extends RuntimeException {
  public IdNotFoundException(String message) {
    super(message);
  }
}
