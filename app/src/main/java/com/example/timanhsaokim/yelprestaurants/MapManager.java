package com.example.timanhsaokim.yelprestaurants;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Location;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.timanhsaokim.yelprestaurants.model.RestaurantInfoWindowAdapter;
import com.example.timanhsaokim.yelprestaurants.model.reviews.Reviews;
import com.example.timanhsaokim.yelprestaurants.model.reviews.ReviewsAdapter;
import com.example.timanhsaokim.yelprestaurants.model.reviews.ReviewsRequest;
import com.example.timanhsaokim.yelprestaurants.model.reviews.ReviewsResponse;
import com.example.timanhsaokim.yelprestaurants.model.search.SearchRequest;
import com.example.timanhsaokim.yelprestaurants.model.search.SearchResponse;
import com.example.timanhsaokim.yelprestaurants.model.search.Business;
import com.example.timanhsaokim.yelprestaurants.model.search.Position;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.VisibleRegion;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by TimAnhSaoKim on 4/30/2017.
 */

public class MapManager {

    private static final String LOG_TAG = "MapManager";

    private GoogleMap mMap;
    private SearchService searchService;
    private Map<Marker, Business> markersAndBusinesses;
    private LatLng defaultLocation = new LatLng(-34, 151);
    private BottomSheetBehavior bottomSheetBehavior;
    private boolean isMapMovedByUser;
    private int MAX_RADIUS;
    private MapsActivity mapsActivity;

    public MapManager(GoogleMap mMap) {
        this.mMap = mMap;
    }

    public void setSearchService(SearchService searchService) {
        this.searchService = searchService;
        this.updateRestaurants(defaultLocation, MAX_RADIUS);
    }

    public boolean initMap(MapsActivity activity){
        this.mapsActivity = activity;

        MAX_RADIUS = mapsActivity.getResources().getInteger(R.integer.maxSearchRadius);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 8F));
        markersAndBusinesses = new HashMap<>();
        GoogleMap.OnCameraMoveStartedListener cameraMoveStartedListener = new GoogleMap.OnCameraMoveStartedListener() {
            @Override
            public void onCameraMoveStarted(int reason) {
                hideBottomWindow();
                Log.e(LOG_TAG, "Move reason: " + reason);
                if (reason == GoogleMap.OnCameraMoveStartedListener.REASON_GESTURE) {
                    isMapMovedByUser = true;
                }

            }
        };
        mMap.setOnCameraMoveStartedListener(cameraMoveStartedListener);

        GoogleMap.OnCameraIdleListener onCameraIdle = new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                LatLng center = mMap.getCameraPosition().target;
                Log.d(LOG_TAG, "Longitude: " + center.longitude + " Latitude" + center.latitude);
                int radius = getMapRadius();
                if(radius > MAX_RADIUS){
                    Log.e(LOG_TAG, "Radius larger than MAX_RADIUS: " + radius);
                    radius = MAX_RADIUS;
                }
                if(isMapMovedByUser){
                    updateRestaurants(center, radius);
                    isMapMovedByUser = false;
                    Log.e(LOG_TAG, "User moves the map");
                }
            }
        };
        mMap.setOnCameraIdleListener(onCameraIdle);

        View infoWindow = mapsActivity.getLayoutInflater().inflate(R.layout.custom_info_window, null);
        View infoContent = mapsActivity.getLayoutInflater().inflate(R.layout.custom_info_content, null);
        RestaurantInfoWindowAdapter infoWindowAdapter = new RestaurantInfoWindowAdapter(infoWindow, infoContent, markersAndBusinesses);
        mMap.setInfoWindowAdapter(infoWindowAdapter);

        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) mapsActivity.findViewById(R.id.main_content);
        final View bottomSheet = coordinatorLayout.findViewById(R.id.restaurant_info);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        hideBottomWindow();
        GoogleMap.OnInfoWindowClickListener infoWindowClickListener = new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                if(bottomSheetBehavior != null){
                    renderDetailResInfo(bottomSheet, marker);
                    int resInfoHeight = bottomSheet.getHeight();
                    Log.d(LOG_TAG, "Restaurant detail window height: " + resInfoHeight);
                    bottomSheetBehavior.setPeekHeight(resInfoHeight);
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                Log.d(LOG_TAG, "Info window is clicked");

            }
        };
        mMap.setOnInfoWindowClickListener(infoWindowClickListener);

        GoogleMap.OnMapClickListener mapClickListener = new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                hideBottomWindow();
            }
        };
        mMap.setOnMapClickListener(mapClickListener);
        return true;
    }

    private boolean updateRestaurants(LatLng center, int radius){
        this.drawCircle(center, radius);

        SearchResponse nearbyRestaurants = getNearbyRestaurant(center, radius);
        if(nearbyRestaurants != null && nearbyRestaurants.getTotal() > 0){
            List<Business> businesses = nearbyRestaurants.getBusinesses();
            for(Business business : businesses){
                BigDecimal distance = business.getDistance();
                if(distance.doubleValue() < radius){
                    Position coordinate = business.getCoordinates();
                    LatLng resPosition = new LatLng(coordinate.getLatitude().doubleValue(), coordinate.getLongitude().doubleValue());
                    String resName = business.getName();
                    Marker marker = mMap.addMarker(new MarkerOptions()
                            .position(resPosition)
                            .title(resName));
                    markersAndBusinesses.put(marker, business);
                }
            }
        }
        return true;
    }

    private SearchResponse getNearbyRestaurant(LatLng center, int radius) {
        SearchRequest request = new SearchRequest("", new BigDecimal(center.latitude), new BigDecimal(center.longitude));
        request.setRadius(radius);
        return searchService.search(request);
    }

    private int getMapRadius(){
        VisibleRegion visibleRegion = mMap.getProjection().getVisibleRegion();

        LatLng farRight = visibleRegion.farRight;
        LatLng farLeft = visibleRegion.farLeft;
        LatLng nearRight = visibleRegion.nearRight;
        LatLng nearLeft = visibleRegion.nearLeft;

        float[] distanceWidth = new float[2];
        Location.distanceBetween(
                (farRight.latitude+nearRight.latitude)/2,
                (farRight.longitude+nearRight.longitude)/2,
                (farLeft.latitude+nearLeft.latitude)/2,
                (farLeft.longitude+nearLeft.longitude)/2,
                distanceWidth
        );


        float[] distanceHeight = new float[2];
        Location.distanceBetween(
                (farRight.latitude+nearRight.latitude)/2,
                (farRight.longitude+nearRight.longitude)/2,
                (farLeft.latitude+nearLeft.latitude)/2,
                (farLeft.longitude+nearLeft.longitude)/2,
                distanceHeight
        );

        float distance;

        if (distanceWidth[0]>distanceHeight[0]){
            distance = distanceWidth[0];
        } else {
            distance = distanceHeight[0];
        }
        return Math.round(distance);
    }

    private boolean drawCircle(LatLng centerPoint, int radius){
        mMap.clear();
        mMap.addCircle(new CircleOptions()
                .center(centerPoint)
                .radius(radius)
                .strokeColor(Color.GREEN));
        return true;
    }

    private View renderDetailResInfo(View resInfo, Marker selectedMarker){
        Business currRestaurant = markersAndBusinesses.get(selectedMarker);
        if(currRestaurant != null){
            TextView resName = (TextView) resInfo.findViewById(R.id.detail_restaurant_name);
            ImageView resImage = (ImageView) resInfo.findViewById(R.id.detail_restaurant_image);
            RatingBar rating = (RatingBar) resInfo.findViewById(R.id.detail_restaurant_rating);
            TextView phone = (TextView) resInfo.findViewById(R.id.detail_restaurant_phone);
            TextView address = (TextView) resInfo.findViewById(R.id.detail_restaurant_address);
            ListView reviews = (ListView) resInfo.findViewById(R.id.detail_restaurant_reviews);

            resName.setText(currRestaurant.getName());

            if(currRestaurant.getRating() != null){
                rating.setMax(5);
                rating.setStepSize(0.5f);
                rating.setRating(currRestaurant.getRating().floatValue());
            }

            String phoneValue = currRestaurant.getPhone();
            if(!phoneValue.isEmpty()){
                phone.setText(phoneValue);
            }

            if(currRestaurant.getLocation() != null){
                String addressValue = currRestaurant.getLocation().getAddress1();
                if(!addressValue.isEmpty()) {
                    address.setText(addressValue);
                }
            }

            ImageAsyncTask getImageTask = new ImageAsyncTask();
            try {
                Bitmap resImageSource = getImageTask.execute(currRestaurant.getImageURL()).get();
                resImage.setImageBitmap(resImageSource);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            String resID = currRestaurant.getId();
            ReviewsRequest request = new ReviewsRequest(resID, null);
            ReviewsResponse response = searchService.getReviews(request);
            if(response != null && response.getTotal() > 0){
                ReviewsAdapter reviewsAdapter = new ReviewsAdapter(mapsActivity.getBaseContext(), 0, response.getReviews());
                reviews.setAdapter(reviewsAdapter);
                reviewsAdapter.notifyDataSetChanged();
            }
        }
        return resInfo;
    }

    private void hideBottomWindow(){
        if(bottomSheetBehavior != null){
            bottomSheetBehavior.setPeekHeight(0);
        }
    }
}
