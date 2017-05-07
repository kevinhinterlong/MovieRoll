package com.hinterlong.kevin.cs126.movieinfoparser.adapters;

import android.view.View;

import com.hinterlong.kevin.cs126.movieinfoparser.MediaItemUtils;
import com.hinterlong.kevin.cs126.movieinfoparser.items.TitledImage;
import com.hinterlong.kevin.cs126.movieinfoparser.model.tmdb.MediaItem;
import com.hinterlong.kevin.cs126.movieinfoparser.view.MovieDetailView;

/**
 * Created by kevin on 4/1/2017.
 */

public class MovieTitledImage implements TitledImage.TitledImageAdapter {
    private MediaItem mediaItem;

    public MovieTitledImage(MediaItem mediaItem) {
        this.mediaItem = mediaItem;
    }

    @Override
    public String getTitle() {
        return mediaItem.getTitle();
    }

    @Override
    public String getImageUrl() {
        return MediaItemUtils.getPoster(mediaItem.getPosterPath());
    }

    @Override
    public View.OnClickListener openDetailed() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieDetailView.openDetailedView(v.getContext(), mediaItem);
            }
        };
    }
}
