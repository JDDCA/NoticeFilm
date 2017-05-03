package com.gmail.nf.project.jddca.noticefilm.view.fragment.upcoming;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.nf.project.jddca.noticefilm.R;
import com.gmail.nf.project.jddca.noticefilm.model.pojos.Film;
import com.gmail.nf.project.jddca.noticefilm.presenter.upcoming.UpcomingMoviePresenter;
import com.gmail.nf.project.jddca.noticefilm.presenter.upcoming.adapter.UpcomingAdapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class UpcomingMovieFragment extends Fragment implements UpcomingView {

    private final String TAG = getClass().getSimpleName();
    public static final String UPCOMING_TAG = "upcoming_tag";

    private UpcomingMoviePresenter presenter;
    private UpcomingAdapter mUpcomingAdapter;
    private Unbinder unbinder;
    private RecyclerView mRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new UpcomingMoviePresenter(this);
        presenter.setFragment(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upcoming_films, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter.getUpcoming();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.upcoming_rv);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void showMovieList(List<Film> movies) {
        mUpcomingAdapter = new UpcomingAdapter(movies);
        mRecyclerView.setAdapter(mUpcomingAdapter);
    }

    @Override
    public void showError(Throwable throwable) {

    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter = null;
        mUpcomingAdapter = null;
    }
}
