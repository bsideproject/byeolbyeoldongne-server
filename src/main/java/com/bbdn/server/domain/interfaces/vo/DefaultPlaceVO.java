package com.bbdn.server.domain.interfaces.vo;

import com.bbdn.server.domain.interfaces.spec.Place;
import com.bbdn.server.domain.enums.PlaceServiceTypeEnums;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DefaultPlaceVO implements Place {
    private final String id;
    private final String placeName;
    private final String placeUrl;
    private final CategoryVO category;
    private final String phone;
    private final String distance;
    private final Double x;
    private final Double y;
    private final String addressName;
    private final String roadAddressName;

    @Builder
    public DefaultPlaceVO(String id, String placeName,
                          String placeUrl, CategoryVO category, String phone, String distance, Double x,
                          Double y,
                          String addressName, String roadAddressName) {
        this.id = id;
        this.placeName = placeName;
        this.placeUrl = placeUrl;
        this.category = category;
        this.phone = phone;
        this.distance = distance;
        this.x = x;
        this.y = y;
        this.addressName = addressName;
        this.roadAddressName = roadAddressName;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getPlaceName() {
        return this.placeName;
    }

    @Override
    public String getPlaceUrl() {
        return this.placeUrl;
    }

    @Override
    public String getCategoryGroupName() {
        return this.category.getGroup().getName();
    }

    @Override
    public String getCategoryGroupCode() {
        return this.category.getGroup().getCode();
    }

    @Override
    public String getCategoryName() {
        return this.category.getName();
    }

    @Override
    public PlaceServiceTypeEnums getPlaceServiceType() {
        return this.category.getGroup().getPlaceServiceTypeEnums();
    }

    @Override
    public String getPhone() {
        return this.phone;
    }

    @Override
    public String getDistance() {
        return this.distance;
    }

    @Override
    public String getAddressName() {
        return this.addressName;
    }

    @Override
    public String getRoadAddressName() {
        return this.roadAddressName;
    }

    @Override
    public Double getX() {
        return this.x;
    }

    @Override
    public Double getY() {
        return this.y;
    }
}
