package com.hinterlong.kevin.cs126.movieinfoparser.model.tmdb;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * Created by kevin on 3/28/2017.
 */

@Parcel(Parcel.Serialization.BEAN)
public class Person {
    @SerializedName("birthday") private String birthday;
    @SerializedName("adult") private boolean adult;
    @SerializedName("biography") private String biography;
    @SerializedName("homepage") private String homepage;
    @SerializedName("profile_path") private String profilePath;
    @SerializedName("id") private int id;
    @SerializedName("also_known_as") private String[] alsoKnownAs;
    @SerializedName("deathday") private String deathday;
    @SerializedName("imdb_id") private String imdbId;
    @SerializedName("name") private String name;
    @SerializedName("gender") private int gender;
    @SerializedName("place_of_birth") private String placeOfBirth;
    @SerializedName("popularity") private double popularity;

    @ParcelConstructor
    public Person(
            String birthday,
            boolean adult,
            String biography,
            String homepage,
            String profilePath,
            int id,
            String[] alsoKnownAs,
            String deathday,
            String imdbId,
            String name,
            int gender,
            String placeOfBirth,
            double popularity
    ) {
        this.birthday = birthday;
        this.adult = adult;
        this.biography = biography;
        this.homepage = homepage;
        this.profilePath = profilePath;
        this.id = id;
        this.alsoKnownAs = alsoKnownAs;
        this.deathday = deathday;
        this.imdbId = imdbId;
        this.name = name;
        this.gender = gender;
        this.placeOfBirth = placeOfBirth;
        this.popularity = popularity;
    }

    public String getBirthday() {
        return birthday;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getBiography() {
        return biography;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public int getId() {
        return id;
    }

    public String[] getAlsoKnownAs() {
        return alsoKnownAs;
    }

    public String getDeathday() {
        return deathday;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getName() {
        return name;
    }

    public int getGender() {
        return gender;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public double getPopularity() {
        return popularity;
    }
}
