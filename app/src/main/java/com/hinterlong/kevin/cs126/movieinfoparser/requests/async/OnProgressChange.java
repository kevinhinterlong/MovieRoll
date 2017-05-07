package com.hinterlong.kevin.cs126.movieinfoparser.requests.async;

import java.util.List;

/**
 * Created by kevin on 3/28/2017.
 */

public interface OnProgressChange<E> {
    void onFinish(List<E> items);

    void onAdd(E item);
}
