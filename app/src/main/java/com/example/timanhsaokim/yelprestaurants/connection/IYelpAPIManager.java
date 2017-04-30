package com.example.timanhsaokim.yelprestaurants.connection;

import com.example.timanhsaokim.yelprestaurants.model.SearchResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by TimAnhSaoKim on 4/27/2017.
 */
public interface IYelpAPIManager {
    @GET("/v3/businesses/search")
    Call<SearchResponse> getBusinessSearch(@QueryMap Map<String, String> params);
}
