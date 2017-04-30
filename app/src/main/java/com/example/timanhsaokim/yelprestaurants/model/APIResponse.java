package com.example.timanhsaokim.yelprestaurants.model;

import android.graphics.Region;

import java.util.ArrayList;

/**
 * Created by TimAnhSaoKim on 4/27/2017.
 */

public class APIResponse {
    public Region getRegion() {
        return this.region;
    }
    public void setRegion(Region region) {
        this.region = region;
    }
    Region region;

    public ArrayList<Object> getBusinesses() {
        return this.businesses;
    }
    public void setBusinesses(ArrayList<Object> businesses) {
        this.businesses = businesses;
    }
    ArrayList<Object> businesses;

    public int getTotal() {
        return this.total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    int total;
}
