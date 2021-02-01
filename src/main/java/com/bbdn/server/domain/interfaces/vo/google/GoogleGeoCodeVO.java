package com.bbdn.server.domain.interfaces.vo.google;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class GoogleGeoCodeVO {

    public static final String STATUS_OK = "OK";
    private String status;
    private List<GooglePlaceVO> results;

    public boolean isStatusOK() {
        return STATUS_OK.equals(status);
    }
}
