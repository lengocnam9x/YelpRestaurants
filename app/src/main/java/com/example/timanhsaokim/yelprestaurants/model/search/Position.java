package com.example.timanhsaokim.yelprestaurants.model.search;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created by TimAnhSaoKim on 4/28/2017.
 */

public class Position {

    @JsonProperty
    private BigDecimal latitude;
    @JsonProperty
    private BigDecimal longitude;

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
}
