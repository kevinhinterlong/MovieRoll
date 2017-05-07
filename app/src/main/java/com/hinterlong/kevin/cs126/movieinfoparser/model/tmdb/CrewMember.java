package com.hinterlong.kevin.cs126.movieinfoparser.model.tmdb;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kevin on 3/28/2017.
 */

public class CrewMember {
    @SerializedName("id") private String id;
    @SerializedName("credit_id") private String creditId;
    @SerializedName("department") private String department;
    @SerializedName("name") private String name;
    @SerializedName("job") private String job;
    @SerializedName("profile_path") private String profilePath;

    public CrewMember(String id, String creditId, String department, String name, String job, String profilePath) {
        this.id = id;
        this.creditId = creditId;
        this.department = department;
        this.name = name;
        this.job = job;
        this.profilePath = profilePath;
    }

    public String getId() {
        return id;
    }

    public String getCreditId() {
        return creditId;
    }

    public String getDepartment() {
        return department;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public String getProfilePath() {
        return profilePath;
    }
}
