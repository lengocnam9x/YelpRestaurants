package com.example.timanhsaokim.yelprestaurants.model.reviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.timanhsaokim.yelprestaurants.ImageAsyncTask;
import com.example.timanhsaokim.yelprestaurants.R;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by TimAnhSaoKim on 5/7/2017.
 */

public class ReviewsAdapter extends ArrayAdapter<Reviews> {

    private static final String LOG_TAG = "ReviewsAdapter";

    private Context context;
    private List<Reviews> reviews;

    public ReviewsAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Reviews> objects) {
        super(context, resource, objects);
        this.context = context;
        this.reviews = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.reviews, parent, false);
        }
        Reviews currentReviews = reviews.get(position);
        if(currentReviews != null){
            TextView userName = (TextView) convertView.findViewById(R.id.userName);
            String userNameValue = currentReviews.getUser().getName();
            userName.setText(userNameValue);

            try {
                ImageAsyncTask getImageTask = new ImageAsyncTask();
                Bitmap resImageSource = getImageTask.execute(currentReviews.getUser().getImageURL()).get();
                ImageView userAvatar = (ImageView) convertView.findViewById(R.id.userAvatar);
                userAvatar.setImageBitmap(resImageSource);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            TextView userComment = (TextView) convertView.findViewById(R.id.userComment);
            userComment.setText(currentReviews.getText());

            RatingBar userRating = (RatingBar) convertView.findViewById(R.id.userRating);
            userRating.setRating(currentReviews.getRating());
            userRating.setMax(5);

            TextView reviewsTime = (TextView) convertView.findViewById(R.id.reviewsTime);
            reviewsTime.setText(currentReviews.getTimeCreated());
        }
        return convertView;
    }

    @Override
    public int getCount() {
        return reviews.size();
    }

    @Nullable
    @Override
    public Reviews getItem(int position) {
        return reviews.get(position);
    }
}
