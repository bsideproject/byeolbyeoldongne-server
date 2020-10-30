package com.chdlsp.datapractice.domain.interfaces.spec;


import com.chdlsp.datapractice.domain.enums.PlaceServiceTypeEnums;

public interface Place {

    String getId();

    String getPlaceName();

    String getPlaceUrl();

    String getCategoryGroupName();

    String getCategoryGroupCode();

    String getCategoryName();

    PlaceServiceTypeEnums getPlaceServiceType();

    String getPhone();

    String getDistance();

    String getAddressName();

    String getRoadAddressName();

    Double getX();

    Double getY();
}
