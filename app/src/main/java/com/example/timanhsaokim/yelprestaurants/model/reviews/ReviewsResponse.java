package com.example.timanhsaokim.yelprestaurants.model.reviews;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by TimAnhSaoKim on 5/6/2017.
 */

public class ReviewsResponse {
    @JsonProperty
    private int total;
    @JsonProperty
    private List<Reviews> reviews;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Reviews> getReviews() {
        return reviews;
    }

    public void setReviews(List<Reviews> reviews) {
        this.reviews = reviews;
    }
}
