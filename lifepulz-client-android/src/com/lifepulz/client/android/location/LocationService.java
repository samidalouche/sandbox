package com.lifepulz.client.android.location;

import android.content.Context;
import android.location.Criteria;
import android.location.LocationManager;

public class LocationService {
    public static LocationManager getLocationManager(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        String provider = getLocationProvider(locationManager);
        locationManager.getLastKnownLocation(provider);
        return locationManager;
    }

    public static String getLocationProvider(LocationManager locationManager) {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        criteria.setSpeedRequired(false);
        
        String provider = locationManager.getBestProvider(criteria, true);
        return provider;
    }
}
