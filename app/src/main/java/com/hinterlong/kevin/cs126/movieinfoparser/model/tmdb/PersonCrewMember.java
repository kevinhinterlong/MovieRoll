package com.hinterlong.kevin.cs126.movieinfoparser.model.tmdb;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel(Parcel.Serialization.BEAN)
public class PersonCrewMember {

    @SerializedName("adult") private boolean adult;
    @SerializedName("character") private String character;
    @SerializedName("credit_id") private String creditId;
    @SerializedName("id") private Long id;
    @SerializedName("original_title") private String originalTitle;
    @SerializedName("poster_path") private String posterPath;
    @SerializedName("release_date") private String releaseDate;
    @SerializedName("title") private String title;

    @ParcelConstructor
    public PersonCrewMember(
            boolean adult,
            String character,
            String creditId,
            Long id,
            String originalTitle,
            String posterPath,
            String releaseDate,
            String title
    ) {
        this.adult = adult;
        this.character = character;
        this.creditId = creditId;
        this.id = id;
        this.originalTitle = originalTitle;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.title = title;
    }

    public boolean getAdult() {
        return adult;
    }

    public String getCharacter() {
        return character;
    }

    public String getCreditId() {
        return creditId;
    }

    public Long getId() {
        return id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getTitle() {
        return title;
    }


}
