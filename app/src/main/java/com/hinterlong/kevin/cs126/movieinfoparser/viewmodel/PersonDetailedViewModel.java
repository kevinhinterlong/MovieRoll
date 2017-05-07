package com.hinterlong.kevin.cs126.movieinfoparser.viewmodel;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.hinterlong.kevin.cs126.movieinfoparser.APIKey;
import com.hinterlong.kevin.cs126.movieinfoparser.adapters.PersonCastMemberTitledImage;
import com.hinterlong.kevin.cs126.movieinfoparser.items.TitledImage;
import com.hinterlong.kevin.cs126.movieinfoparser.model.tmdb.Person;
import com.hinterlong.kevin.cs126.movieinfoparser.model.tmdb.PersonCastMember;
import com.hinterlong.kevin.cs126.movieinfoparser.model.tmdb.PersonCredits;
import com.hinterlong.kevin.cs126.movieinfoparser.requests.async.FetchGeneric;
import com.hinterlong.kevin.cs126.movieinfoparser.requests.async.OnProgressChange;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;

import java.util.List;

/**
 * Created by kevin on 4/1/2017.
 */

public class PersonDetailedViewModel extends AbstractViewModel {
    public static final String TAG = PersonDetailedViewModel.class.getSimpleName();
    private Person person;
    private RecyclerView recyclerView;
    private FastItemAdapter<TitledImage> actorItemAdapter;

    public PersonDetailedViewModel(RecyclerView recyclerView, Person person) {
        fetchPerson(person.getId());
        this.recyclerView = recyclerView;
        actorItemAdapter = new FastItemAdapter<>();
    }

    public void initialize(Bundle bundle) {
        actorItemAdapter.clear();

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(actorItemAdapter);

        fetchMovieCredits(person.getId());
        actorItemAdapter.withSavedInstanceState(bundle);
    }

    public void fetchPerson(int id) {
        String url = "http://api.themoviedb.org/3/person/" + id + "?api_key=" + APIKey.API_KEY;
        new FetchGeneric<>(new OnProgressChange<Person>() {
            @Override
            public void onFinish(List<Person> people) {

            }

            @Override
            public void onAdd(Person p) {
                person = p;
                fetchMovieCredits(p.getId());
                Log.d(TAG, "loaded " + person.getName());
            }
        }, Person.class).execute(url);
    }

    public void fetchMovieCredits(int id) {
        String url = "http://api.themoviedb.org/3/person/" + id + "/movie_credits?api_key=" + APIKey.API_KEY;
        new FetchGeneric<>(new OnProgressChange<PersonCredits>() {
            @Override
            public void onFinish(List<PersonCredits> items) {

            }

            @Override
            public void onAdd(PersonCredits item) {
                for (PersonCastMember cast : item.getCast()) {
                    addMovieFromCredits(cast);
                }
            }
        }, PersonCredits.class).execute(url);
    }

    private void addMovieFromCredits(PersonCastMember cast) {
        actorItemAdapter.add(actorItemAdapter.getAdapterItemCount(), new TitledImage(new PersonCastMemberTitledImage(cast)));
        actorItemAdapter.notifyAdapterDataSetChanged();
    }

    public Bundle saveInstanceState(Bundle outState) {
        return actorItemAdapter.saveInstanceState(outState);
    }
}
