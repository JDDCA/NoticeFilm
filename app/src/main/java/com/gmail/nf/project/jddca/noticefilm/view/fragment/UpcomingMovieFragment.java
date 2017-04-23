package com.gmail.nf.project.jddca.noticefilm.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.nf.project.jddca.noticefilm.R;
import com.gmail.nf.project.jddca.noticefilm.model.pojos.Film;
import com.gmail.nf.project.jddca.noticefilm.model.pojos.UpcomingMovie;
import com.gmail.nf.project.jddca.noticefilm.presenter.UpcomingMoviePresenter;
import com.gmail.nf.project.jddca.noticefilm.view.UpcomingView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class UpcomingMovieFragment extends Fragment implements UpcomingView {

    private final String TAG = getClass().getSimpleName();

    private UpcomingMoviePresenter presenter;
    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new UpcomingMoviePresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upcoming_films, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter.getUpcoming();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void showUpcomingMoviesList(List<UpcomingMovie> movies) {
        for (int i = 0; i < movies.size(); i++) {
            Film film = movies.get(i).getResults().get(i);
            Log.i(TAG, "showUpcomingMoviesList: " + film.toString());
        }
    }

    @Override
    public void showError(Throwable throwable) {

    }
}
