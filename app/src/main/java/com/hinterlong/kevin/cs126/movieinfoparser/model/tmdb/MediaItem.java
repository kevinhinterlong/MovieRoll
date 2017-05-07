package com.hinterlong.kevin.cs126.movieinfoparser.model.tmdb;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.Parcel.Serialization;
import org.parceler.ParcelConstructor;

import java.util.HashSet;

/**
 * @author kjh2
 *         Java Object to hold JSON information
 */
@Parcel(Serialization.BEAN)
public class MediaItem {
    @SerializedName("vote_average") private final double voteAverage;
    @SerializedName("backdrop_path") private final String backdropPath;
    @SerializedName("adult") private final boolean adult;
    @SerializedName("id") private final int id;
    @SerializedName("title") private final String title;
    @SerializedName("overview") private final String overview;
    @SerializedName("original_language") private final String originalLanguage;
    @SerializedName("genre_ids") private final HashSet<Integer> genreIds;
    @SerializedName("release_date") private final String releaseDate;
    @SerializedName("original_title") private final String originalTitle;
    @SerializedName("vote_count") private final int voteCount;
    @SerializedName("poster_path") private final String posterPath;
    @SerializedName("video") private final boolean video;
    @SerializedName("popularity") private final double popularity;

    @ParcelConstructor
    public MediaItem(
            double voteAverage,
            String backdropPath,
            boolean adult,
            int id,
            String title,
            String overview,
            String originalLanguage,
            HashSet<Integer> genreIds,
            String releaseDate,
            String originalTitle,
            int voteCount,
            String posterPath,
            boolean video,
            double popularity
    ) {
        this.voteAverage = voteAverage;
        this.backdropPath = backdropPath;
        this.adult = adult;
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.originalLanguage = originalLanguage;
        this.genreIds = genreIds;
        this.releaseDate = releaseDate;
        this.originalTitle = originalTitle;
        this.voteCount = voteCount;
        this.posterPath = posterPath;
        this.video = video;
        this.popularity = popularity;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public HashSet<Integer> getGenreIds() {
        return genreIds;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public boolean isVideo() {
        return video;
    }

    public double getPopularity() {
        return popularity;
    }

    /**
     * Only check movie IDs for equality
     *
     * @param o to be compared
     * @return true if equal
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof MediaItem && ((MediaItem) o).getId() == getId();
    }
}
