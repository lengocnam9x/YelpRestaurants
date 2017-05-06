package com.example.timanhsaokim.yelprestaurants.model.search;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by TimAnhSaoKim on 4/28/2017.
 */

public class Region {

    @JsonProperty
    private Position center;

    public Position getCenter() {
        return center;
    }

    public void setCenter(Position center) {
        this.center = center;
    }
}
