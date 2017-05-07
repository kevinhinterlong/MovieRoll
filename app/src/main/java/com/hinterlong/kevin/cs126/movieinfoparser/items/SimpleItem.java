package com.hinterlong.kevin.cs126.movieinfoparser.items;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.hinterlong.kevin.cs126.movieinfoparser.viewmodel.AbstractViewModel;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.mikepenz.fastadapter.utils.ViewHolderFactory;

import java.util.List;

/**
 * Created by kevin on 4/1/2017.
 */

public abstract class SimpleItem<Model> extends AbstractItem<SimpleItem, SimpleViewHolder> {
    private static final ViewHolderFactory<? extends SimpleViewHolder> FACTORY = new ItemFactory();
    private AbstractViewModel viewModel;
    private int layoutRes;

    public SimpleItem(AbstractViewModel viewModel, int layoutRes) {
        this.viewModel = viewModel;
        this.layoutRes = layoutRes;
    }

    public SimpleItem(Model model, int layoutRes) {
        viewModel = getViewModel(model);
        this.layoutRes = layoutRes;
    }

    @Override
    public void bindView(SimpleViewHolder holder, List<Object> payloads) {
        super.bindView(holder, payloads);
        holder.bind(viewModel);
    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public int getLayoutRes() {
        return layoutRes;
    }

    @Override
    public ViewHolderFactory<? extends SimpleViewHolder> getFactory() {
        return FACTORY;
    }

    public abstract AbstractViewModel getViewModel(Model model);

    protected static class ItemFactory implements ViewHolderFactory<SimpleViewHolder> {
        public SimpleViewHolder create(View v) {
            return new SimpleViewHolder(DataBindingUtil.bind(v));
        }
    }
}
