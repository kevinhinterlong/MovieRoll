package com.hinterlong.kevin.cs126.movieinfoparser.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.hinterlong.kevin.cs126.movieinfoparser.R;
import com.hinterlong.kevin.cs126.movieinfoparser.databinding.ActivityMovieDetailViewBinding;
import com.hinterlong.kevin.cs126.movieinfoparser.model.tmdb.MediaItem;
import com.hinterlong.kevin.cs126.movieinfoparser.viewmodel.MovieDetailedViewModel;
import com.hinterlong.kevin.cs126.movieinfoparser.viewmodel.MovieItemViewModel;

import org.parceler.Parcels;

public class MovieDetailView extends AppCompatActivity {
    public final static String TAG = MovieDetailView.class.getSimpleName();
    public static final String MOVIE = "MOVIE_EXTRA";
    public static final String MOVIE_ID = "MOVIE_EXTRA_ID";

    private ActivityMovieDetailViewBinding detailViewBinding;
    private MovieDetailedViewModel detailedViewModel;

    public static void openDetailedView(Context context, MediaItem movieItem) {
        Intent intent = new Intent(context, MovieDetailView.class);
        intent.putExtra(MOVIE, Parcels.wrap(movieItem));
        context.startActivity(intent);
    }

    public static void openDetailedView(Context context, int movieItem) {
        Intent intent = new Intent(context, MovieDetailView.class);
        intent.getIntExtra(MOVIE_ID, movieItem);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail_view);

        setSupportActionBar(detailViewBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        Log.d(TAG, "bundle = " + bundle);
        if (bundle != null) {
            if (bundle.containsKey(MOVIE)) {
                MediaItem mediaItem = Parcels.unwrap(bundle.getParcelable(MOVIE));
                initializeViews(mediaItem, savedInstanceState);
            } else if (bundle.containsKey(MOVIE_ID)) {
                int id = bundle.getInt(MOVIE_ID);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState = detailedViewModel.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initializeViews(MediaItem mediaItem, Bundle bundle) {
        initializeBinders(mediaItem);

        detailedViewModel.initialize(bundle);
        detailViewBinding.movieContentDetailed.setDetailedViewModel(detailedViewModel);

        setTitle(mediaItem.getTitle());
    }

    private void initializeBinders(MediaItem mediaItem) {
        MovieItemViewModel viewModel = new MovieItemViewModel(mediaItem);
        detailViewBinding.setMovie(viewModel);
        detailViewBinding.movieContentDetailed.setMovie(viewModel);

        detailedViewModel = new MovieDetailedViewModel(
                detailViewBinding.movieContentDetailed.relatedMoviesList,
                detailViewBinding.movieContentDetailed.actorsList,
                mediaItem,
                viewModel);
    }
}
