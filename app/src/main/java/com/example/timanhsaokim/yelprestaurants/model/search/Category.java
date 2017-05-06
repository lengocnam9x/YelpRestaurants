package com.example.timanhsaokim.yelprestaurants.model.search;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by TimAnhSaoKim on 4/28/2017.
 */

public class Category {
    @JsonProperty
    private String alias;
    @JsonProperty
    private String title;

    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
