package com.hinterlong.kevin.cs126.movieinfoparser.viewmodel;

import android.databinding.Bindable;
import android.view.View;

import com.hinterlong.kevin.cs126.movieinfoparser.items.TitledImage;

/**
 * Created by kevin on 4/1/2017.
 */

public class TitledImageViewModel extends AbstractViewModel {
    private TitledImage.TitledImageAdapter model;

    public TitledImageViewModel(TitledImage.TitledImageAdapter titledImageAdapter) {
        model = titledImageAdapter;
    }

    @Bindable
    public String getTitle() {
        return model.getTitle();
    }

    @Bindable
    public String getImageUrl() {
        return model.getImageUrl();
    }

    public View.OnClickListener openDetailed() {
        return model.openDetailed();
    }
}
