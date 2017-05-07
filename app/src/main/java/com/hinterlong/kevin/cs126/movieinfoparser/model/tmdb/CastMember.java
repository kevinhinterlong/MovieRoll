package com.hinterlong.kevin.cs126.movieinfoparser.model.tmdb;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * Created by kevin on 3/28/2017.
 */
@Parcel(Parcel.Serialization.BEAN)
public class CastMember {
    @SerializedName("id") private String id;
    @SerializedName("order") private String order;
    @SerializedName("credit_id") private String creditId;
    @SerializedName("name") private String name;
    @SerializedName("cast_id") private String castId;
    @SerializedName("profile_path") private String profilePath;

    @ParcelConstructor
    public CastMember(String id, String order, String creditId, String name, String castId, String profilePath) {
        this.id = id;
        this.order = order;
        this.creditId = creditId;
        this.name = name;
        this.castId = castId;
        this.profilePath = profilePath;
    }

    public String getId() {
        return id;
    }

    public String getOrder() {
        return order;
    }

    public String getCreditId() {
        return creditId;
    }

    public String getName() {
        return name;
    }

    public String getCastId() {
        return castId;
    }

    public String getProfilePath() {
        return profilePath;
    }
}
