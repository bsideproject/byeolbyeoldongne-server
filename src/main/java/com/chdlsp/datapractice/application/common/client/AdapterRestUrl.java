package com.chdlsp.datapractice.application.common.client;

import org.springframework.http.HttpMethod;

public interface AdapterRestUrl {
  //
  String getUrl();
  HttpMethod getMethod();
}
