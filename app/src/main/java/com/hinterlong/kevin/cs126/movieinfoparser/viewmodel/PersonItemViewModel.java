package com.hinterlong.kevin.cs126.movieinfoparser.viewmodel;

import android.databinding.Bindable;

import com.hinterlong.kevin.cs126.movieinfoparser.APIKey;
import com.hinterlong.kevin.cs126.movieinfoparser.MediaItemUtils;
import com.hinterlong.kevin.cs126.movieinfoparser.model.tmdb.Person;
import com.hinterlong.kevin.cs126.movieinfoparser.requests.async.FetchGeneric;
import com.hinterlong.kevin.cs126.movieinfoparser.requests.async.OnProgressChange;

import java.util.List;

/**
 * Created by kevin on 4/1/2017.
 */

public class PersonItemViewModel extends AbstractViewModel {
    private Person person;

    public PersonItemViewModel(Person person) {
        this.person = person;
        fetchPerson(person.getId());
    }

    public void fetchPerson(int id) {
        String url = "http://api.themoviedb.org/3/person/" + id + "?api_key=" + APIKey.API_KEY;
        new FetchGeneric<>(new OnProgressChange<Person>() {
            @Override
            public void onFinish(List<Person> people) {

            }

            @Override
            public void onAdd(Person p) {
                setPerson(p);
            }
        }, Person.class).execute(url);
    }

    public void setPerson(Person p) {
        person = p;
        notifyChange();
    }

    @Bindable
    public String getName() {
        return person.getName();
    }

    @Bindable
    public String getBiography() {
        return person.getBiography();
    }

    @Bindable
    public String getBirthday() {
        return person.getBirthday();
    }

    @Bindable
    public String getProfile() {
        return MediaItemUtils.getPoster(person.getProfilePath());
    }

    @Bindable
    public String getWebUrl() {
        return MediaItemUtils.getPersonUrl(person.getId());
    }

    @Bindable
    public String getPopularity() {
        return String.valueOf(person.getPopularity());
    }
}
