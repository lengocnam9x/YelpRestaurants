package com.example.timanhsaokim.yelprestaurants.model.search;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by TimAnhSaoKim on 4/29/2017.
 */

public class Location {
    @JsonProperty
    private String address1;

    @JsonProperty
    private String address2;

    @JsonProperty
    private String address3;

    @JsonProperty
    private String city;

    @JsonProperty
    private String country;

    @JsonProperty("display_address")
    private String[] displayAddress;

    @JsonProperty
    private String state;

    @JsonProperty("zip_code")
    private String zipCode;

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String[] getDisplayAddress() {
        return displayAddress;
    }

    public void setDisplayAddress(String[] displayAddress) {
        this.displayAddress = displayAddress;
    }
}
