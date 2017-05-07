package com.hinterlong.kevin.cs126.movieinfoparser.requests.async;

import android.os.AsyncTask;
import android.util.Log;

import com.hinterlong.kevin.cs126.movieinfoparser.model.tmdb.MediaItem;
import com.hinterlong.kevin.cs126.movieinfoparser.requests.JSONWebTask;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by kevin on 3/28/2017.
 */

public class FetchGeneric<E> extends AsyncTask<String, E, List<E>> {
    public static final String TAG = FetchGeneric.class.getSimpleName();
    private static final Map<String, Object> cache = new HashMap<>();
    private static ExecutorService executorService;
    private OnProgressChange<E> onProgressChange;
    private Class<E> eClass;

    public FetchGeneric(OnProgressChange<E> onProgressChange, Class<E> eClass) {
        this.onProgressChange = onProgressChange;
        this.eClass = eClass;
        if (executorService == null) {
            int availableProcessors = Runtime.getRuntime().availableProcessors();
            Log.d(TAG, "Running on " + availableProcessors + " processors");
            executorService = Executors.newFixedThreadPool(Math.max(availableProcessors, 1));
        }
    }

    @Override
    protected void onPostExecute(List<E> credits) {
        super.onPostExecute(credits);
        onProgressChange.onFinish(credits);
    }

    @SafeVarargs
    @Override
    protected final void onProgressUpdate(E... values) {
        super.onProgressUpdate(values);
        for (E e : values) {
            onProgressChange.onAdd(e);
        }
    }

    @Override
    protected List<E> doInBackground(String... params) {
        List<E> toReturn = new ArrayList<>();
        getAllItems(params, toReturn);
        return toReturn;
    }

    private void getAllItems(String[] urlsToFetch, final List<E> moviesToReturn) {
        try {
            List<Future> futures = new ArrayList<>();
            for (final String url : urlsToFetch) {
                Future future = executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        E toAdd = getItem(url);
                        if (toAdd != null) {
                            moviesToReturn.add(toAdd);
                            publishProgress(toAdd);
                        }
                    }
                });
                futures.add(future);
            }
            for (Future<?> f : futures) {
                try {
                    f.get();
                } catch (InterruptedException e) {
                    Log.e(TAG, "Failed to finish download task - interrupted");
                } catch (ExecutionException e) {
                    e.printStackTrace();
                    Log.e(TAG, "Failed to finish download task - executionException. " + e.getCause());
                }
            }
        } finally {
        }
    }

    /**
     * Fetches a list of movies from the URL
     *
     * @param url to query.
     * @return List of {@link MediaItem}
     */
    private E getItem(String url) {
        if (cache.containsKey(url)) {
            Log.d(TAG, "fetching " + url + " from cache");
            return (E) cache.get(url);
        }
        E toReturn = null;
        try {
            URL toDownload = new URL(url);
            toReturn = JSONWebTask.readJSONFromUrl(toDownload, eClass);
        } catch (MalformedURLException e) { //bad url
            Log.d(TAG, "Failed to build url from: " + url);
            e.printStackTrace();
        } catch (IOException e) { //network exception
            Log.d(TAG, "Could not connect to: " + url);
            e.printStackTrace();
        }

        cache.put(url, toReturn);
        return toReturn;
    }
}
