package com.hinterlong.kevin.cs126.movieinfoparser.items;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

import com.android.databinding.library.baseAdapters.BR;

/**
 * Created by kevin on 4/1/2017.
 * https://medium.com/google-developers/android-data-binding-recyclerview-db7c40d9f0e4
 */

public class SimpleViewHolder extends RecyclerView.ViewHolder {
    private final ViewDataBinding binding;

    public SimpleViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Object obj) {
        binding.setVariable(BR.obj, obj);
        binding.executePendingBindings();
    }
}
