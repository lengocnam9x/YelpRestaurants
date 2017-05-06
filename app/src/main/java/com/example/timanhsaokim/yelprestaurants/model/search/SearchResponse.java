package com.example.timanhsaokim.yelprestaurants.model.search;

import com.example.timanhsaokim.yelprestaurants.model.search.Business;
import com.example.timanhsaokim.yelprestaurants.model.search.Region;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by TimAnhSaoKim on 4/27/2017.
 */

public class SearchResponse {
    @JsonProperty
    private Region region;
    @JsonProperty
    private int total;
    @JsonProperty
    private List<Business> businesses;

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Business> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(List<Business> businesses) {
        this.businesses = businesses;
    }
}
