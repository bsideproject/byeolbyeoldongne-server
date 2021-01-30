package com.bbdn.server.domain.interfaces.vo.google;

import com.google.gson.Gson;
import lombok.ToString;

import java.util.List;

@ToString
public class GeocodeDTO {

    /**
     * Takes the JSON response string returned by the Geocoder.find method,
     * parses it and returns a GResponse object hierarchy with the information.
     *
     * @param jsonGeocodeResponse Geocoding find response in the JSON format.
     * @return an instance of GResponse containing the information from the
     * JSON response.
     */
    public static GeocodeResponse parse(String jsonGeocodeResponse) {
        GeocodeResponse responseAsObjects = new Gson().fromJson(jsonGeocodeResponse,
                GeocodeResponse.class);
        return responseAsObjects;
    }
}

class GResult {
    List<GAddressComponent> address_components;
    String formatted_address;
    GGeometry geometry;
    Boolean partial_match;
    String place_id;
    List<String> types;
}
class GGeometry {
    GViewport bounds;
    GCoordinates location;
    String location_type;
    GViewport viewport;
}
class GViewport {
    GCoordinates northeast;
    GCoordinates southwest;
}
class GAddressComponent {
    String long_name;
    String short_name;
    List<String> types;
}
class GCoordinates {
    String lat;
    String lng;
}