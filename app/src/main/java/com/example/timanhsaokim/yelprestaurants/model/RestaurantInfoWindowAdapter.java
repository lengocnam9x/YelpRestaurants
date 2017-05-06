package com.example.timanhsaokim.yelprestaurants.model;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.timanhsaokim.yelprestaurants.ImageAsyncTask;
import com.example.timanhsaokim.yelprestaurants.R;
import com.example.timanhsaokim.yelprestaurants.model.search.Business;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by TimAnhSaoKim on 4/30/2017.
 */

public class RestaurantInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private static final String LOG_TAG = "MapManager";

    private final View mWindow;
    private final View mContents;
    private Map<Marker, Business> markersAndBusinesses;

    public RestaurantInfoWindowAdapter(View mWindow, View mContents, Map markersAndBusinesses) {
        this.mWindow = mWindow;
        this.mContents = mContents;
        this.markersAndBusinesses = markersAndBusinesses;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        renderContent(markersAndBusinesses.get(marker));
        return mWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        Log.d(LOG_TAG, "get info content");
        return null;
    }

    private boolean renderContent(Business currentRestaurant){

        if(isRestaurantDataAllSet(currentRestaurant)){
            ImageView resImage = (ImageView) mWindow.findViewById(R.id.restaurantImage);
            TextView resName = (TextView) mWindow.findViewById(R.id.restaurantName);
            RatingBar rating = (RatingBar) mWindow.findViewById(R.id.ratingBar);
            TextView address = (TextView) mWindow.findViewById(R.id.address);

            resName.setText(currentRestaurant.getName());

            if(currentRestaurant.getRating() != null){
                rating.setMax(5);
                rating.setStepSize(0.5f);
                rating.setRating(currentRestaurant.getRating().floatValue());
            }
            if(currentRestaurant.getLocation() != null) {
                address.setText(currentRestaurant.getLocation().getAddress1());
            }
            ImageAsyncTask getImageTask = new ImageAsyncTask();
            try {
                Bitmap resImageSource = getImageTask.execute(currentRestaurant.getImageURL()).get();
                resImage.setImageBitmap(resImageSource);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    private boolean isRestaurantDataAllSet(Business currentRestaurant){
        return currentRestaurant != null && currentRestaurant.getName() != null;
    }
}
