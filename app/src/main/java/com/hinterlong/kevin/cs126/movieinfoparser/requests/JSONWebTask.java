package com.hinterlong.kevin.cs126.movieinfoparser.requests;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by kevin on 1/31/2017.
 */
public class JSONWebTask {
    private static final long PAUSE_MS_HTTP_ERROR_429 = TimeUnit.SECONDS.toMillis(10);
    private static Map<URL, Object> downloadCache = new HashMap<>();

    public static <E> E readJSONFromUrl(URL url, Class<E> type) throws IOException {
        if (downloadCache.containsKey(url)) {
            return (E) downloadCache.get(url);
        }
        E toFetch;
        HttpURLConnection conn;

        conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) { //source: drake eidukas
            System.err.println("Connection failed - Response: " + conn.getResponseCode() + " " + conn.getResponseMessage());
        }
        if (conn.getResponseCode() == 429 || Integer.parseInt(conn.getHeaderField("X-RateLimit-Remaining")) <= 1) { //source: drake eidukas
            System.out.println("Rate limit reached: waiting 10 seconds.");
            try {
                Thread.sleep(PAUSE_MS_HTTP_ERROR_429);
            } catch (InterruptedException ignored) {
            } finally {
                conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("GET");
            }
        }
        InputStreamReader inputStreamReader = new InputStreamReader(conn.getInputStream(), Charset.defaultCharset());

        JsonReader jsonReader = new JsonReader(inputStreamReader);
        Gson gson = new Gson();
        toFetch = gson.fromJson(jsonReader, type);

        downloadCache.put(url, toFetch);
        return toFetch;
    }
}
