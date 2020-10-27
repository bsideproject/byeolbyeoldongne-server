package com.chdlsp.datapractice.application.common.client;

import org.springframework.http.ResponseEntity;

public interface AdapterRestClient {
  <T> T sendAndReceive(RequestBuilder requestBuilder);
  <T> ResponseEntity<T> sendAndReceiveEntity(RequestBuilder requestBuilder);
}
