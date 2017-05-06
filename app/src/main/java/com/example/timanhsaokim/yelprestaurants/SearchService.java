package com.example.timanhsaokim.yelprestaurants;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.timanhsaokim.yelprestaurants.connection.ConnectionFactory;
import com.example.timanhsaokim.yelprestaurants.connection.IYelpAPIManager;
import com.example.timanhsaokim.yelprestaurants.model.reviews.ReviewsRequest;
import com.example.timanhsaokim.yelprestaurants.model.reviews.ReviewsResponse;
import com.example.timanhsaokim.yelprestaurants.model.search.SearchRequest;
import com.example.timanhsaokim.yelprestaurants.model.search.SearchResponse;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;

/**
 * Created by TimAnhSaoKim on 4/30/2017.
 */

public class SearchService extends Service {

    ConnectionFactory connectionFactory = new ConnectionFactory();
    private final IBinder mBinder = new SearchServiceBinder();
    private IYelpAPIManager client;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        if(client == null){
            String appID = getString(R.string.appID);
            String appSecret = getString(R.string.appSecret);
            try {
                client = connectionFactory.createAPI(appID, appSecret);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return START_STICKY;
    }

    public SearchResponse search(SearchRequest request){
        Call<SearchResponse> call = client.getBusinessSearch(request.getFieldNameAndValue());
        SearchResponse response = new SearchResponse();
        SearchAsyncTask asyncTask = new SearchAsyncTask();
        try {
            response = (SearchResponse) asyncTask.execute(call).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return response;
    }

    public class SearchServiceBinder extends Binder {
        SearchService getService() {
            return SearchService.this;
        }
    }

    public ReviewsResponse getReviews(ReviewsRequest reviewsRequest){
        ReviewsResponse response = new ReviewsResponse();
        Call<ReviewsResponse> call = client.getBusinessReviews(reviewsRequest.getId(), reviewsRequest.getLocale());
        SearchAsyncTask asyncTask = new SearchAsyncTask();
        try {
            response = (ReviewsResponse) asyncTask.execute(call).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
