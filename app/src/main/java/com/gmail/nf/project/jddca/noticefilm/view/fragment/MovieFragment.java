package com.gmail.nf.project.jddca.noticefilm.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.nf.project.jddca.noticefilm.R;
import com.gmail.nf.project.jddca.noticefilm.model.pojos.Movie;
import com.gmail.nf.project.jddca.noticefilm.model.rest.MovieService;
import com.gmail.nf.project.jddca.noticefilm.model.rest.MovieServiceImpl;
import com.gmail.nf.project.jddca.noticefilm.presenter.MainPresenter;
import com.gmail.nf.project.jddca.noticefilm.view.MovieView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import lombok.experimental.PackagePrivate;

/**
 * A simple {@link Fragment} subclass.
 */
@PackagePrivate
public class MovieFragment extends Fragment implements MovieView {

    private MainPresenter mainPresenter;

    // ButterKnife биндер для управения привязками к view-элементам
    private Unbinder unbinder;

    // Хранит значение, полученное после генерации случайного числа
    private int randomID;

    @BindView(R.id.movie_img)
    ImageView poster;

    @BindView(R.id.movie_title)
    TextView title;

    @BindView(R.id.movie_description)
    TextView description;

    @BindView(R.id.movie_year_realese)
    TextView year;

    @BindView(R.id.fab_to_favorites)
    FloatingActionButton fabToFavorites;

    @BindView(R.id.fab_generate)
    FloatingActionButton generate;

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainPresenter = new MainPresenter(this, new MovieServiceImpl());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        unbinder = ButterKnife.bind(this, view);
        randomID = mainPresenter.getRandomID();
        generate.setOnClickListener(v -> {
            randomID = mainPresenter.getRandomID();
        });
        mainPresenter.getMovie(randomID);
        return view;
    }

    /**
     * Выводит информацию о фильме на экран
     *
     * @param movie
     */
    @Override
    public void showMovie(Movie movie) {
        // Добавляем картинку используя библиотеку Picasso
        Picasso.with(getActivity())
                .load(MovieServiceImpl.URL + movie.getBackdropPath())
                .into(poster);
        // Присваиваем текстовым полям значения полученные из Presenter'a
        title.setText(movie.getTitle());
        description.setText(movie.getOverview());
        year.setText(movie.getReleaseDate());
    }

    /**
     * Выводит информацию об ошибке на экран
     */
    @Override
    public void showError() {
        Toast.makeText(getActivity(), "What a terrible failure", Toast.LENGTH_SHORT);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
