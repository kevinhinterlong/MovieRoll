package com.hinterlong.kevin.cs126.movieinfoparser.model.tmdb;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel(Parcel.Serialization.BEAN)
public class PersonCastMember {
    @SerializedName("adult")
    private boolean adult;
    @SerializedName("credit_id")
    private String creditId;
    @SerializedName("department")
    private String department;
    @SerializedName("id")
    private int id;
    @SerializedName("job")
    private String job;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("title")
    private String title;

    @ParcelConstructor
    public PersonCastMember(
            boolean adult,
            String creditId,
            String department,
            int id,
            String job,
            String originalTitle,
            String posterPath,
            String releaseDate,
            String title
    ) {
        this.adult = adult;
        this.creditId = creditId;
        this.department = department;
        this.id = id;
        this.job = job;
        this.originalTitle = originalTitle;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.title = title;
    }

    public boolean getAdult() {
        return adult;
    }

    public String getCreditId() {
        return creditId;
    }

    public String getDepartment() {
        return department;
    }

    public int getId() {
        return id;
    }

    public String getJob() {
        return job;
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
