package com.hinterlong.kevin.cs126.movieinfoparser.viewmodel;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.hinterlong.kevin.cs126.movieinfoparser.APIKey;
import com.hinterlong.kevin.cs126.movieinfoparser.adapters.CastMemberTitledImage;
import com.hinterlong.kevin.cs126.movieinfoparser.adapters.MovieTitledImage;
import com.hinterlong.kevin.cs126.movieinfoparser.items.TitledImage;
import com.hinterlong.kevin.cs126.movieinfoparser.model.tmdb.CastMember;
import com.hinterlong.kevin.cs126.movieinfoparser.model.tmdb.Credits;
import com.hinterlong.kevin.cs126.movieinfoparser.model.tmdb.MediaItem;
import com.hinterlong.kevin.cs126.movieinfoparser.model.tmdb.MediaItemQuery;
import com.hinterlong.kevin.cs126.movieinfoparser.requests.async.FetchGeneric;
import com.hinterlong.kevin.cs126.movieinfoparser.requests.async.OnProgressChange;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;

import java.util.List;

/**
 * Created by kevin on 4/1/2017.
 */

public class MovieDetailedViewModel extends AbstractViewModel {
    public static final String TAG = MovieListViewModel.class.getSimpleName();
    private FastItemAdapter<TitledImage> movieItemAdapter;
    private FastItemAdapter<TitledImage> actorItemAdapter;
    private RecyclerView relatedMoviesList;
    private RecyclerView actorsList;
    private MediaItem mediaItem;
    private MovieItemViewModel movie;

    public MovieDetailedViewModel(RecyclerView movies, RecyclerView actors, MediaItem mediaItem, MovieItemViewModel movie) {
        relatedMoviesList = movies;
        actorsList = actors;
        this.mediaItem = mediaItem;
        this.movie = movie;
        movieItemAdapter = new FastItemAdapter<>();
        actorItemAdapter = new FastItemAdapter<>();
    }

    public void initialize(Bundle bundle) {
        movieItemAdapter.clear();
        actorItemAdapter.clear();

        setupRelatedMovies(bundle);
        setupCast(bundle);
    }

    private void setupRelatedMovies(Bundle bundle) {
        Log.d(TAG, "loading related movies");
        relatedMoviesList.setHasFixedSize(true);
        relatedMoviesList.setAdapter(movieItemAdapter);

        fetchRelatedMovies(mediaItem.getId());
        movieItemAdapter.withSavedInstanceState(bundle);
    }

    private void setupCast(Bundle bundle) {
        Log.d(TAG, "loading actors");
        actorsList.setHasFixedSize(true);
        actorsList.setAdapter(actorItemAdapter);

        fetchCast(mediaItem.getId());
        movieItemAdapter.withSavedInstanceState(bundle);
    }

    private void fetchCast(int id) {
        String url = "http://api.themoviedb.org/3/movie/" + id + "/credits?api_key=" + APIKey.API_KEY;
        new FetchGeneric<>(new OnProgressChange<Credits>() {
            @Override
            public void onFinish(List<Credits> creditsList) {
                for (Credits credits : creditsList) {
                    for (CastMember castMember : credits.getCast()) {
                        addCastMember(castMember);
                    }
                }
            }

            @Override
            public void onAdd(Credits item) {

            }
        }, Credits.class).execute(url);
    }

    private void addCastMember(CastMember castMember) {
        actorItemAdapter.add(actorItemAdapter.getAdapterItemCount(),
                new TitledImage(new CastMemberTitledImage(castMember)));
    }

    private void fetchRelatedMovies(int id) {
        int currentPage = 1;
        Log.d(TAG, "loading movies related movies");
        String url = "http://api.themoviedb.org/3/movie/" + id + "/recommendations?api_key=" + APIKey.API_KEY + "&language=en-US&page=" + currentPage;
        new FetchGeneric<>(new OnProgressChange<MediaItemQuery>() {
            @Override
            public void onFinish(List<MediaItemQuery> mediaItems) {
                for (MediaItemQuery miq : mediaItems) {
                    for (final MediaItem m : miq.getResults()) {
                        addMovie(m);
                    }
                }
            }

            @Override
            public void onAdd(MediaItemQuery item) {
            }
        }, MediaItemQuery.class).execute(url);
    }

    public void addMovie(final MediaItem movie) {
        movieItemAdapter.add(movieItemAdapter.getAdapterItemCount(),
                new TitledImage(new MovieTitledImage(movie)));
    }

    public Bundle saveInstanceState(Bundle outState) {
        return movieItemAdapter.saveInstanceState(outState);
    }
}
