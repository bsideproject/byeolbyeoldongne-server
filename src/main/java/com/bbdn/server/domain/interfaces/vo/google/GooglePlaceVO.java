package com.bbdn.server.domain.interfaces.vo.google;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GooglePlaceVO {

    private String[] types;
    private String formatted_address;
    private AddressComponent[] address_components;
    private Geometry geometry;
    private String place_id;

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formattedAddress) {
        formatted_address = formattedAddress;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public String getCountry() {
        if (null != address_components && address_components.length > 0) {
            for (AddressComponent addressComponent : address_components) {
                if (containString(addressComponent.types, "country")) {
                    return addressComponent.long_name;
                }
            }
        }
        return "";
    }

    public String getProvince() {
        if (null != address_components) {
            for (AddressComponent addressComponent : address_components) {
                if (containString(addressComponent.types, "administrative_area_level_1")) {
                    return addressComponent.long_name;
                }
            }
        }
        return "";
    }

    public String getCity() {
        if (null != address_components && address_components.length > 2) {
            String city = "";
            for (AddressComponent addressComponent : address_components) {
                if (containString(addressComponent.types, "locality")) {
                    return addressComponent.long_name;
                }
            }
        }
        return "";
    }

    public String getStreet() {
        if (null != address_components) {
            for (AddressComponent addressComponent : address_components) {
                if (containString(addressComponent.types, "route")) {
                    return addressComponent.long_name;
                }
            }
        }
        return "";
    }

    private boolean containString(String[] array, String target) {
        for (String string : array) {
            if (target.equalsIgnoreCase(string)) {
                return true;
            }
        }
        return false;
    }

    public static class AddressComponent {
        String long_name;
        String short_name;
        String[] types;
    }

    public static class Geometry {
        public Bounds bounds;
        public String location_type ;
        public Location location;
        public Bounds viewport;
    }

    public static class Bounds {
        public Location northeast ;
        public Location southwest ;
    }

    public static class Location {
        public Double lat ;
        public Double lng ;
    }
}