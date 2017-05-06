package com.example.timanhsaokim.yelprestaurants;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by TimAnhSaoKim on 5/6/2017.
 */

public class DetailRestaurantActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_info);
    }
}
