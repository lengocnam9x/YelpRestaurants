package com.example.timanhsaokim.yelprestaurants.connection;

import com.example.timanhsaokim.yelprestaurants.model.AccessToken;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by TimAnhSaoKim on 4/27/2017.
 */

public interface IAuthAPI {
    @POST("/oauth2/token")
    Call<AccessToken> getToken(@Query("grant_type") String grantType,
                               @Query("client_id") String clientId,
                               @Query("client_secret") String clientSecret);
}
