package com.example.timanhsaokim.yelprestaurants.connection;

import com.example.timanhsaokim.yelprestaurants.SearchAsyncTask;
import com.example.timanhsaokim.yelprestaurants.model.AccessToken;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by TimAnhSaoKim on 4/27/2017.
 */

public class ConnectionFactory {
    private static final String API_URL = "https://api.yelp.com";

    private OkHttpClient httpClient;
    private AccessToken accessToken;
    IYelpAPIManager client;

    public IYelpAPIManager createAPI(String clientId, String clientSecret) throws IOException, ExecutionException, InterruptedException {
        if(client != null && accessToken.getExpiresIn() > 0 ){
            return client;
        }
        this.getAccessToken(clientId, clientSecret);
        Retrofit retrofit = builtRetrofit();
        System.out.println("Create API Access Token: " + accessToken.getExpiresIn());
        return retrofit.create(IYelpAPIManager.class);
    }

    public AccessToken getAccessToken(String clientId, String clientSecret) throws IOException, ExecutionException, InterruptedException {
        if(accessToken != null && accessToken.getExpiresIn() > 0){
            return accessToken;
        }

        OkHttpClient authClient = new OkHttpClient.Builder()
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(getJacksonFactory())
                .client(authClient)
                .build();

        IAuthAPI client = retrofit.create(IAuthAPI.class);
        Call<AccessToken> call = client.getToken("client_credentials", clientId, clientSecret);
        SearchAsyncTask asyncTask = new SearchAsyncTask();
        accessToken = (AccessToken) asyncTask.execute(call).get();
        System.out.println("Get Access token: " + accessToken.getExpiresIn());
        return accessToken;
    }

    private boolean initHttpClient(){
        if(accessToken != null){
            AccessTokenInterceptor accessTokenInterceptor = new AccessTokenInterceptor(accessToken);
            httpClient = new OkHttpClient.Builder()
                    .addInterceptor(accessTokenInterceptor)
                    .build();
            return true;
        }
        return false;
    }

    private Retrofit builtRetrofit(){
        if(httpClient == null){
            this.initHttpClient();
        }
        return new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(getJacksonFactory())
                .client(httpClient)
                .build();
    }

    private static JacksonConverterFactory getJacksonFactory(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return JacksonConverterFactory.create(mapper);
    }
}
