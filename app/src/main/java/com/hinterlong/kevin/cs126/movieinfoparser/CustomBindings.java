package com.hinterlong.kevin.cs126.movieinfoparser;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by kevin on 4/1/2017.
 */

public class CustomBindings {
    @BindingAdapter(value = {"app:image_url", "app:image_error"}, requireAll = false)
    public static void loadImage(ImageView imageView, String url, int placeholder) {
        Glide.with(imageView.getContext())
                .load(url)
                .error(placeholder)
                .into(imageView);
    }

    @BindingAdapter("app:web_url")
    public static void loadWebPage(View view, final String url) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(url))
                );
            }
        });
    }
}
