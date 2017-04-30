package com.example.timanhsaokim.yelprestaurants;

import android.os.AsyncTask;
import android.os.Process;

import com.example.timanhsaokim.yelprestaurants.connection.ConnectionFactory;
import com.example.timanhsaokim.yelprestaurants.connection.IYelpAPIManager;
import com.example.timanhsaokim.yelprestaurants.model.SearchRequest;
import com.example.timanhsaokim.yelprestaurants.model.SearchResponse;

import java.io.IOException;
import java.math.BigDecimal;

import retrofit2.Call;

/**
 * Created by TimAnhSaoKim on 4/27/2017.
 */

public class SearchAsyncTask extends AsyncTask<Call, Process, Object> {

    @Override
    protected Object doInBackground(Call... params) {
        try {
            Call call = params[0];
            if(!call.isExecuted()){
                return call.execute().body();
            } else {
                return call.clone().execute().body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object result) {

    }
}
