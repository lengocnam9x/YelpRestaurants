package com.example.timanhsaokim.yelprestaurants.test;

import com.example.timanhsaokim.yelprestaurants.model.SearchRequest;

import java.math.BigDecimal;

/**
 * Created by TimAnhSaoKim on 4/29/2017.
 */

public class TestSearchRequest {
    public static void main(String[] args) {
        SearchRequest searchRequest = new SearchRequest("HCM", new BigDecimal(1000), new BigDecimal(1000));
        System.out.println(searchRequest.getFieldNameAndValue());
    }
}
