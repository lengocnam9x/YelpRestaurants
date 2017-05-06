package com.example.timanhsaokim.yelprestaurants.connection;

import com.example.timanhsaokim.yelprestaurants.model.reviews.ReviewsResponse;
import com.example.timanhsaokim.yelprestaurants.model.search.SearchResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by TimAnhSaoKim on 4/27/2017.
 */
public interface IYelpAPIManager {
    @GET("/v3/businesses/search")
    Call<SearchResponse> getBusinessSearch(@QueryMap Map<String, String> params);

    @GET("/v3/businesses/{id}/reviews")
    Call<ReviewsResponse> getBusinessReviews(@Path("id") String id, @Query("locale") String locale);
}
