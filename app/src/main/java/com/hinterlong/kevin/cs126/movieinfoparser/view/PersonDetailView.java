package com.hinterlong.kevin.cs126.movieinfoparser.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hinterlong.kevin.cs126.movieinfoparser.R;
import com.hinterlong.kevin.cs126.movieinfoparser.databinding.ActivityPersonDetailViewBinding;
import com.hinterlong.kevin.cs126.movieinfoparser.model.tmdb.CastMember;
import com.hinterlong.kevin.cs126.movieinfoparser.model.tmdb.Person;
import com.hinterlong.kevin.cs126.movieinfoparser.viewmodel.PersonDetailedViewModel;
import com.hinterlong.kevin.cs126.movieinfoparser.viewmodel.PersonItemViewModel;

import org.parceler.Parcels;

public class PersonDetailView extends AppCompatActivity {
    public final static String TAG = MovieDetailView.class.getSimpleName();
    public static final String CAST = "CAST_EXTRA";

    private ActivityPersonDetailViewBinding binding;
    private PersonDetailedViewModel personDetailedViewModel;
    private PersonItemViewModel personItemViewModel;

    public static void openDetailedView(Context context, CastMember castMember) {
        Intent intent = new Intent(context, PersonDetailView.class);
        intent.putExtra(CAST, Parcels.wrap(castMember));
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_person_detail_view);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            try {
                CastMember castMember = Parcels.unwrap(bundle.getParcelable(CAST));
                initializeViews(castMember);
                personDetailedViewModel.initialize(savedInstanceState);
            } catch (Exception e) {

            }
        }
    }

    private void initializeViews(CastMember castMember) {
        Person person = getPersonFromCastMember(castMember);
        personItemViewModel = new PersonItemViewModel(person);
        personDetailedViewModel = new PersonDetailedViewModel(binding.included.actorsList, person);

        binding.setPerson(personItemViewModel);
        binding.included.setPerson(personItemViewModel);
        binding.included.setDetailedPerson(personDetailedViewModel);
        setTitle(castMember.getName());
    }

    private Person getPersonFromCastMember(CastMember castMember) {
        return new Person(
                "",
                false,
                "",
                "",
                castMember.getProfilePath(),
                Integer.parseInt(castMember.getId()),
                null,
                "",
                "",
                "",
                -1,
                "",
                0
        );
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState = personDetailedViewModel.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
