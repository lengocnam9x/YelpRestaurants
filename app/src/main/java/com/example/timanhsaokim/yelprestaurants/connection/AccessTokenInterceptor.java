package com.example.timanhsaokim.yelprestaurants.connection;

import com.example.timanhsaokim.yelprestaurants.model.AccessToken;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by TimAnhSaoKim on 4/29/2017.
 */

public class AccessTokenInterceptor implements Interceptor {

    private AccessToken accessToken;

    public AccessTokenInterceptor(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        request = request.newBuilder()
                .header("Accept", "application/json")
                .header("Authorization", accessToken.getTokenType() + " " + accessToken.getAccessToken())
                .build();
        System.out.println("Request: " + request);
        return chain.proceed(request);
    }
}
