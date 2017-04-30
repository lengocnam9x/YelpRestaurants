package com.example.timanhsaokim.yelprestaurants;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by TimAnhSaoKim on 4/30/2017.
 */

public class YelpLocationListener implements LocationListener {
    private static final String TAG = "YelpLocationListener";
    @Override
    public void onLocationChanged(Location location) {
        String longitude = "Longitude: " + location.getLongitude();
        Log.v(TAG, longitude);
        String latitude = "Latitude: " + location.getLatitude();
        Log.v(TAG, latitude);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
