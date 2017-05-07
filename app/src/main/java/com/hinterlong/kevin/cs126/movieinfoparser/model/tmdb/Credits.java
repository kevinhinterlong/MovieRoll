package com.hinterlong.kevin.cs126.movieinfoparser.model.tmdb;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kevin on 3/28/2017.
 */

public class Credits {
    @SerializedName("id") private String id;
    @SerializedName("cast") private List<CastMember> cast;
    @SerializedName("crew") private List<CrewMember> crew;

    public Credits(String id, List<CastMember> cast, List<CrewMember> crew) {
        this.id = id;
        this.cast = cast;
        this.crew = crew;
    }

    public String getId() {
        return id;
    }

    public List<CastMember> getCast() {
        return cast;
    }

    public List<CrewMember> getCrew() {
        return crew;
    }
}
