package com.bbdn.server.application.common.client;

import org.springframework.http.HttpMethod;

public interface AdapterRestUrl {

  String getUrl();
  HttpMethod getMethod();
}
