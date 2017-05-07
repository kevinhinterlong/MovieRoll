package com.hinterlong.kevin.cs126.movieinfoparser;

import java.text.MessageFormat;

/**
 * Provides a helpful set of functions to build urls.
 */
public class MediaItemUtils {
    public static final String IMAGE_BASE = "https://image.tmdb.org/t/p/";
    public static final String IMAGE_PATH = IMAGE_BASE + "{0}/{1}";
    public static final String W_185 = "w185";
    public static final String W_600 = "w600";
    public static final String TMDB_BASE = "https://www.themoviedb.org/";
    public static final String TMDB_MOVIE = TMDB_BASE + "movie/{0}";
    public static final String TMDB_PERSON = TMDB_BASE + "person/{0}";

    private MediaItemUtils() {

    }

    /**
     * Gets a poster image url.
     *
     * @param input path to poster image
     * @return String url to poster image
     */
    public static String getPoster(String input) {
        if (input == null || input.length() <= 1) {
            return "";
        }
        return MessageFormat.format(IMAGE_PATH, W_185, input.substring(1));
    }

    /**
     * Gets a backdrop image url.
     *
     * @param backdropPath path to backdrop image
     * @return String url to backdrop image
     */
    public static String getBackdrop(String backdropPath) {
        return MessageFormat.format(IMAGE_PATH, W_600, backdropPath);
    }

    /**
     * Gets a url to a more detailed webpage of the movie.
     *
     * @param id TMDB id of the movie
     * @return String url to webpage.
     */
    public static String getMovieUrl(int id) {
        return MessageFormat.format(TMDB_MOVIE, String.valueOf(id));
    }

    public static String getPersonUrl(int id) {
        return MessageFormat.format(TMDB_PERSON, String.valueOf(id));
    }
}
