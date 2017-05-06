package com.example.timanhsaokim.yelprestaurants.model.reviews;

/**
 * Created by TimAnhSaoKim on 5/6/2017.
 */

public class ReviewsRequest {
    private String id;
    private String locale;

    public ReviewsRequest() {
    }

    public ReviewsRequest(String id, String locale) {
        this.id = id;
        this.locale = locale;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
