package com.example.timanhsaokim.yelprestaurants.model.search;

import com.example.timanhsaokim.yelprestaurants.KeyAndValueGenerator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by TimAnhSaoKim on 4/28/2017.
 */

public class SearchRequest extends KeyAndValueGenerator {
    private String term;
    private String location;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private int radius;
    private String categories;
    private String locale;
    private String limit;
    private int offset;
    private String sort_by;
    private String price;
    private boolean open_now;
    private int open_at;
    private String attributes;

    public SearchRequest() {
    }

    public SearchRequest(String location, BigDecimal latitude, BigDecimal longitude) {
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

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

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getSort_by() {
        return sort_by;
    }

    public void setSort_by(String sort_by) {
        this.sort_by = sort_by;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isOpen_now() {
        return open_now;
    }

    public void setOpen_now(boolean open_now) {
        this.open_now = open_now;
    }

    public int getOpen_at() {
        return open_at;
    }

    public void setOpen_at(int open_at) {
        this.open_at = open_at;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }
}
