package com.example.timanhsaokim.yelprestaurants;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.IBinder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.timanhsaokim.yelprestaurants.model.SearchRequest;
import com.example.timanhsaokim.yelprestaurants.model.SearchResponse;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.VisibleRegion;

import java.math.BigDecimal;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String LOG_TAG = "FragmentActivity";
    private MapManager mapManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent searchServiceIntent = new Intent(getBaseContext(), SearchService.class);
        startService(searchServiceIntent);
        bindService(searchServiceIntent, serviceConnection, Context.BIND_AUTO_CREATE);


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapManager = new MapManager(googleMap);
        mapManager.initMap(this);
    }


    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            SearchService.SearchServiceBinder searchServiceBinder = (SearchService.SearchServiceBinder) binder;
            mapManager.setSearchService(searchServiceBinder.getService());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mapManager.setSearchService(null);
        }
    };

}
