package com.example.timanhsaokim.yelprestaurants.model.reviews;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by TimAnhSaoKim on 5/6/2017.
 */

public class Reviews {
    @JsonProperty
    private String text;
    @JsonProperty
    private String url;
    @JsonProperty
    private int rating;
    @JsonProperty("time_created")
    private String timeCreated;
    @JsonProperty
    private User user;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(String timeCreated) {
        this.timeCreated = timeCreated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
