package com.hinterlong.kevin.cs126.movieinfoparser.viewmodel;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.hinterlong.kevin.cs126.movieinfoparser.APIKey;
import com.hinterlong.kevin.cs126.movieinfoparser.R;
import com.hinterlong.kevin.cs126.movieinfoparser.items.MovieItem;
import com.hinterlong.kevin.cs126.movieinfoparser.model.tmdb.MediaItem;
import com.hinterlong.kevin.cs126.movieinfoparser.model.tmdb.MediaItemQuery;
import com.hinterlong.kevin.cs126.movieinfoparser.requests.async.FetchGeneric;
import com.hinterlong.kevin.cs126.movieinfoparser.requests.async.OnProgressChange;
import com.mikepenz.fastadapter.adapters.FooterAdapter;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;
import com.mikepenz.fastadapter_extensions.items.ProgressItem;
import com.mikepenz.fastadapter_extensions.scroll.EndlessRecyclerOnScrollListener;

import java.text.MessageFormat;
import java.util.List;

/**
 * Created by kevin on 4/1/2017.
 */

public class MovieListViewModel extends AbstractViewModel {
    public static final String TAG = MovieListViewModel.class.getSimpleName();
    private Context context;
    private FastItemAdapter<MovieItem> movies;
    private FooterAdapter<ProgressItem> footerAdapter;
    private int currentPage = 1;
    private RecyclerView recyclerView;

    public MovieListViewModel(RecyclerView recyclerView, Context context) {
        this.context = context;
        this.recyclerView = recyclerView;
    }

    public void intialize(Bundle bundle) {
        movies = new FastItemAdapter<>();
        footerAdapter = new FooterAdapter<>();

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(footerAdapter.wrap(movies));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(footerAdapter) {
            @Override
            public void onLoadMore(int currentPage) {
                footerAdapter.clear();
                footerAdapter.add(new ProgressItem().withEnabled(false));
                fetchNewMovies(1);
            }
        });

        movies.withSavedInstanceState(bundle);
        if (movies.getItemCount() == 0) {
            fetchNewMovies(1);
        }
    }

    private void fetchNewMovies(int numberOfQueriesToMake) {
        Log.d(TAG, "loading recyclerView from currentPage " + currentPage + " of api");
        String urlPattern = context.getString(R.string.tmdb_api_base) + "{0}?api_key=" + APIKey.API_KEY + "&page={1}";
        String urls[] = new String[numberOfQueriesToMake];
        for (int i = 0; i < numberOfQueriesToMake; i++) {
            urls[i] = MessageFormat.format(urlPattern, "movie/popular/", currentPage);
            currentPage++;
        }
        new FetchGeneric<>(new OnProgressChange<MediaItemQuery>() {
            @Override
            public void onFinish(List<MediaItemQuery> mediaItems) {
                footerAdapter.clear();
            }

            @Override
            public void onAdd(MediaItemQuery item) {
                for (MediaItem mediaItem : item.getResults()) {
                    movies.add(movies.getAdapterItemCount(), new MovieItem(mediaItem));
                }
            }
        }, MediaItemQuery.class).execute(urls);
    }

    public Bundle saveInstanceState(Bundle outState) {
        return movies.saveInstanceState(outState);
    }
}
