package com.hinterlong.kevin.cs126.movieinfoparser.model.tmdb;

import java.util.List;

/**
 * @author kjh2
 *         Java Object to hold JSON information
 */
public class MediaItemQuery {
    private List<MediaItem> results;
    private int page;
    private int total_pages;
    private int total_results;

    public List<MediaItem> getResults() {
        return results;
    }

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return total_pages;
    }

    public int getTotalResults() {
        return total_results;
    }
}
