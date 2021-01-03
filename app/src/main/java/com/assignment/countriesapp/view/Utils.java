package com.assignment.countriesapp.view;

import android.app.DownloadManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.assignment.countriesapp.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

public class Utils {

    public static void getImage(ImageView imageView, String url, CircularProgressDrawable circularProgressDrawable){
        RequestOptions options=new RequestOptions()
                .placeholder(circularProgressDrawable)
                .error(R.mipmap.ic_launcher_round);

        Glide.with(imageView.getContext())
                .setDefaultRequestOptions(options)
                .load(url)
                .into(imageView);
    }
    public static CircularProgressDrawable getProgressDrawable(Context context) {
        CircularProgressDrawable progressDrawable = new CircularProgressDrawable(context);
        progressDrawable.setStrokeWidth(10f);
        progressDrawable.setCenterRadius(50f);
        progressDrawable.start();
        return progressDrawable;
    }
}
