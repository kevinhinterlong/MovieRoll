package com.hinterlong.kevin.cs126.movieinfoparser.items;

import com.hinterlong.kevin.cs126.movieinfoparser.R;
import com.hinterlong.kevin.cs126.movieinfoparser.model.tmdb.MediaItem;
import com.hinterlong.kevin.cs126.movieinfoparser.viewmodel.AbstractViewModel;
import com.hinterlong.kevin.cs126.movieinfoparser.viewmodel.MovieItemViewModel;

/**
 * Created by kevin on 3/28/2017.
 */

public class MovieItem extends SimpleItem<MediaItem> {

    public static final int MOVIE_LIST_ITEM = R.layout.movie_list_item;

    public MovieItem(MovieItemViewModel mediaItem) {
        super(mediaItem, MOVIE_LIST_ITEM);
    }

    public MovieItem(MediaItem mediaItem) {
        super(mediaItem, MOVIE_LIST_ITEM);
    }

    @Override
    public AbstractViewModel getViewModel(MediaItem model) {
        return new MovieItemViewModel(model);
    }
}
