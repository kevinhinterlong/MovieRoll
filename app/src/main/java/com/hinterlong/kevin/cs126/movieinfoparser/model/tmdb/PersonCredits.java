package com.hinterlong.kevin.cs126.movieinfoparser.model.tmdb;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.util.List;

/**
 * Created by kevin on 3/28/2017.
 */
@Parcel(Parcel.Serialization.BEAN)
public class PersonCredits {
    @SerializedName("id") private String id;
    @SerializedName("cast") private List<PersonCastMember> cast;
    @SerializedName("crew") private List<PersonCrewMember> crew;

    @ParcelConstructor
    public PersonCredits(String id, List<PersonCastMember> cast, List<PersonCrewMember> crew) {
        this.id = id;
        this.cast = cast;
        this.crew = crew;
    }

    public String getId() {
        return id;
    }

    public List<PersonCastMember> getCast() {
        return cast;
    }

    public List<PersonCrewMember> getCrew() {
        return crew;
    }
}
