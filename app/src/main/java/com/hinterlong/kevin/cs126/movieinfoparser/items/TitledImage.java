package com.hinterlong.kevin.cs126.movieinfoparser.items;

import android.view.View;

import com.hinterlong.kevin.cs126.movieinfoparser.R;
import com.hinterlong.kevin.cs126.movieinfoparser.viewmodel.AbstractViewModel;
import com.hinterlong.kevin.cs126.movieinfoparser.viewmodel.TitledImageViewModel;

/**
 * Created by kevin on 3/28/2017.
 */

public class TitledImage extends SimpleItem<TitledImage.TitledImageAdapter> {

    public static final int TITLED_IMAGE = R.layout.titled_image_item;

    public TitledImage(TitledImageViewModel simpleViewHolder) {
        super(simpleViewHolder, TITLED_IMAGE);
    }

    public TitledImage(TitledImageAdapter titledImageAdapter) {
        super(titledImageAdapter, TITLED_IMAGE);
    }

    @Override
    public AbstractViewModel getViewModel(TitledImageAdapter model) {
        return new TitledImageViewModel(model);
    }

    public interface TitledImageAdapter {
        String getTitle();

        String getImageUrl();

        View.OnClickListener openDetailed();
    }
}
