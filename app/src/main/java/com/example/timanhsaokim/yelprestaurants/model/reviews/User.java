package com.example.timanhsaokim.yelprestaurants.model.reviews;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by TimAnhSaoKim on 5/6/2017.
 */

public class User {
    @JsonProperty
    private String name;
    @JsonProperty("image_url")
    private String imageURL;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
