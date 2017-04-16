package com.gmail.nf.project.jddca.noticefilm.view.fragment;


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.nf.project.jddca.noticefilm.R;
import com.gmail.nf.project.jddca.noticefilm.model.pojos.Movie;
import com.gmail.nf.project.jddca.noticefilm.model.rest.MovieServiceImpl;
import com.gmail.nf.project.jddca.noticefilm.presenter.MainPresenter;
import com.gmail.nf.project.jddca.noticefilm.view.MovieView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import lombok.experimental.PackagePrivate;

/**
 * A simple {@link Fragment} subclass.
 */
@PackagePrivate
public class MovieFragment extends Fragment implements MovieView {

    private final String TAG = getClass().getSimpleName();

    private final String PATH = "https://image.tmdb.org/t/p/w500";

    private MainPresenter mainPresenter;

    // ButterKnife биндер для управения привязками к view-элементам
    private Unbinder unbinder;

    // Хранит значение, полученное после генерации случайного числа
    private int randomID;

    @BindView(R.id.movie_img)
    ImageView poster;

    @BindView(R.id.movie_constraint_layout)
    ConstraintLayout constraintLayout;

    @BindView(R.id.movie_title)
    TextView title;

    @BindView(R.id.original_title)
    TextView originalTitle;

    @BindView(R.id.movie_description)
    TextView description;

    @BindView(R.id.movie_year_realese)
    TextView year;

    @BindView(R.id.fab_to_favorites)
    FloatingActionButton fabToFavorites;

    @BindView(R.id.generate_btn)
    Button generateBtn;

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
        generateBtn.setOnClickListener(v -> {
            randomID = mainPresenter.getRandomID();
        });
        mainPresenter.getMovie(500);
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
        Log.e(TAG, PATH + movie.getPosterPath());
        Picasso.with(getContext())
                .load(PATH + movie.getPosterPath())
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        constraintLayout.setBackground(new BitmapDrawable(getContext().getResources(), bitmap));
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
        Picasso.with(getContext())
                .load(PATH + movie.getPosterPath())
                .centerCrop()
                .resize(poster.getMeasuredWidth(), poster.getMeasuredHeight())
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        poster.setBackground(new BitmapDrawable(getContext().getResources(), bitmap));
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
        // Присваиваем текстовым полям значения полученные из Presenter'a
        originalTitle.setText(movie.getOriginalTitle());
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
