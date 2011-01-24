package com.lifepulz.client.android.gateway.model;

import org.apache.http.HttpEntity;

/**
 * Wrap a location object
 * 
 * "location": { "longitude": -74.0, "latitude": 35.0, "country": "CAD", "city":
 * "Montreal" },
 * 
 * @author joelgrenon
 * 
 */
public class Location extends JsonBackedModel {

    public Location(HttpEntity body) {
        super(body);
    }

    public Location(android.location.Location location) {
        setLongitude(location.getLongitude());
        setLatitude(location.getLatitude());
    }

    public Double getLongitude() {
        return getDouble("longitude");
    }

    public void setLongitude(Double longitude) {
        setDouble("longitude", longitude);
    }

    public Double getLatitude() {
        return getDouble("latitude");
    }

    public void setLatitude(Double latitude) {
        setDouble("latitude", latitude);
    }

    public String getCountry() {
        return getString("country");
    }

    public void setCountry(String country) {
        setString("country", country);
    }

    public String getCity() {
        return getString("city");
    }

    public void setCity(String city) {
        setString("city", city);
    }
}
