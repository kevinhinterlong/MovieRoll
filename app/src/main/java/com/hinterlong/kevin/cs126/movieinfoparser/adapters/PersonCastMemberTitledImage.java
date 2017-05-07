package com.hinterlong.kevin.cs126.movieinfoparser.adapters;

import android.view.View;

import com.hinterlong.kevin.cs126.movieinfoparser.MediaItemUtils;
import com.hinterlong.kevin.cs126.movieinfoparser.items.TitledImage;
import com.hinterlong.kevin.cs126.movieinfoparser.model.tmdb.PersonCastMember;

/**
 * Created by kevin on 4/2/2017.
 */

public class PersonCastMemberTitledImage implements TitledImage.TitledImageAdapter {
    private PersonCastMember personCastMember;

    public PersonCastMemberTitledImage(PersonCastMember personCastMember) {
        this.personCastMember = personCastMember;
    }

    @Override
    public String getTitle() {
        return personCastMember.getTitle();
    }

    @Override
    public String getImageUrl() {
        return MediaItemUtils.getPoster(personCastMember.getPosterPath());
    }

    @Override
    public View.OnClickListener openDetailed() {
        return null; //TODO allow users to click movies here (need to send movie id)
    }
}
