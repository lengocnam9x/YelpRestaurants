package com.example.timanhsaokim.yelprestaurants.model.searchbusinesses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

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
