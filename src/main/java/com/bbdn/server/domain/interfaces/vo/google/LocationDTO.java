package com.bbdn.server.domain.interfaces.vo.google;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationDTO {

    private String id;
    private double lat;
    private double lng;
    private String address;

}
