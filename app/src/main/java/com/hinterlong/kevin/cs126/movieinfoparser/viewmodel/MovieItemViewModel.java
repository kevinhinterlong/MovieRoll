package com.hinterlong.kevin.cs126.movieinfoparser.viewmodel;

import android.databinding.Bindable;
import android.view.View;

import com.hinterlong.kevin.cs126.movieinfoparser.MediaItemUtils;
import com.hinterlong.kevin.cs126.movieinfoparser.model.tmdb.MediaItem;
import com.hinterlong.kevin.cs126.movieinfoparser.view.MovieDetailView;

/**
 * Created by kevin on 4/1/2017.
 */

public class MovieItemViewModel extends AbstractViewModel {
    private MediaItem model;

    public MovieItemViewModel(MediaItem mediaItem) {
        model = mediaItem;
    }

    @Bindable
    public String getPoster() {
        return MediaItemUtils.getPoster(model.getPosterPath());
    }

    @Bindable
    public String getBackdrop() {
        return MediaItemUtils.getBackdrop(model.getBackdropPath());
    }

    @Bindable
    public String getTitle() {
        return model.getTitle();
    }

    @Bindable
    public String getSummary() {
        return model.getOverview();
    }

    @Bindable
    public String getReleaseDate() {
        return model.getReleaseDate();
    }

    @Bindable
    public String getRating() {
        return String.valueOf(model.getVoteAverage());
    }

    @Bindable
    public String getPopularity() {
        return String.valueOf(model.getPopularity());
    }

    @Bindable
    public String getWebUrl() {
        return MediaItemUtils.getMovieUrl(model.getId());
    }

    public View.OnClickListener onOpenDetailed() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieDetailView.openDetailedView(v.getContext(), model);
            }
        };
    }

    public void setMediaItem(MediaItem mediaItem) {
        this.model = mediaItem;
        notifyChange();
    }
}
