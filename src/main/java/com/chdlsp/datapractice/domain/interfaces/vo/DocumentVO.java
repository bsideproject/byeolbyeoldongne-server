package com.chdlsp.datapractice.domain.interfaces.vo;


import lombok.Getter;
import lombok.ToString;

import java.beans.ConstructorProperties;

@ToString
@Getter
public class DocumentVO {

    private final String id;
    private final String place_name;
    private final String category_name;
    private final String category_group_name;
    private final String category_group_code;
    private final String phone;
    private final String address_name;
    private final String road_address_name;
    private final Double x;
    private final Double y;
    private final String place_url;
    private final String distance;

    @ConstructorProperties({"id", "place_name", "category_name", "category_group_name", "category_group_code", "phone",
            "address_name", "road_address_name", "x", "y", "place_url", "distance"})
    public DocumentVO(String id, String place_name, String category_name,
                    String category_group_code, String category_group_name, String phone, String address_name, String road_address_name, Double x,
                    Double y, String place_url, String distance) {
        this.id = id;
        this.place_name = place_name;
        this.category_name = category_name;
        this.category_group_code = category_group_code;
        this.category_group_name = category_group_name;
        this.phone = phone;
        this.address_name = address_name;
        this.road_address_name = road_address_name;
        this.x = x;
        this.y = y;
        this.place_url = place_url;
        this.distance = distance;
    }
}
