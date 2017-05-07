package com.hinterlong.kevin.cs126.movieinfoparser.adapters;

import android.view.View;

import com.hinterlong.kevin.cs126.movieinfoparser.MediaItemUtils;
import com.hinterlong.kevin.cs126.movieinfoparser.items.TitledImage;
import com.hinterlong.kevin.cs126.movieinfoparser.model.tmdb.CastMember;
import com.hinterlong.kevin.cs126.movieinfoparser.view.PersonDetailView;

/**
 * Created by kevin on 4/1/2017.
 */

public class CastMemberTitledImage implements TitledImage.TitledImageAdapter {
    private CastMember castMember;

    public CastMemberTitledImage(CastMember castMember) {
        this.castMember = castMember;
    }

    @Override
    public String getTitle() {
        return castMember.getName();
    }

    @Override
    public String getImageUrl() {
        return MediaItemUtils.getPoster(castMember.getProfilePath());
    }

    @Override
    public View.OnClickListener openDetailed() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonDetailView.openDetailedView(v.getContext(), castMember);
            }
        };
    }
}
