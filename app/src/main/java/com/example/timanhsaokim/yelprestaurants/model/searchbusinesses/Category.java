package com.example.timanhsaokim.yelprestaurants.model.searchbusinesses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

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
