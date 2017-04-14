package com.gmail.nf.project.jddca.noticefilm.view.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.nf.project.jddca.noticefilm.R;

import butterknife.BindView;
import lombok.Setter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    @BindView(R.id.movie_img)
    ImageView image;
    @BindView(R.id.movie_title)
    TextView title;
    @BindView(R.id.movie_description)
    TextView description;
    @BindView(R.id.movie_year)
    TextView year;

    // ID хранит идентификатор текущего фильма | ID keeping identifier of current movie
    @Setter
    private long movieID;

    private static final String MOVIE_ID = "movieID";

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if (view != null) {
            // Реализовать привязку к данным из Репозиториев | Implement binding to data from Repositories
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Сохранение состояния ID фильма при повороте экрана | Save instance of movie ID when screen was rotated
        if (savedInstanceState != null) {
            movieID = savedInstanceState.getLong(MOVIE_ID);
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putLong(MOVIE_ID, movieID);
    }
}
