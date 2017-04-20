package com.gmail.nf.project.jddca.noticefilm.view.fragment;


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.style.CubeGrid;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.gmail.nf.project.jddca.noticefilm.R;
import com.gmail.nf.project.jddca.noticefilm.model.pojos.Film;
import com.gmail.nf.project.jddca.noticefilm.model.pojos.Genre;
import com.gmail.nf.project.jddca.noticefilm.model.utils.DialogFactory;
import com.gmail.nf.project.jddca.noticefilm.model.utils.RetrofitUtils;
import com.gmail.nf.project.jddca.noticefilm.presenter.GeneratePresenter;
import com.gmail.nf.project.jddca.noticefilm.view.MovieViewGenerate;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import lombok.experimental.PackagePrivate;

/**
 * A simple {@link Fragment} subclass.
 */
@PackagePrivate
public class GenerateFragment extends Fragment implements MovieViewGenerate {

    private final String TAG = getClass().getSimpleName();
    public static final String SPINNER_LIST = "com.gmail.nf.project.jddca.noticefilm.view.fragment.SPINNER_LIST ";
    public static final String FILM = "com.gmail.nf.project.jddca.noticefilm.view.fragment.FILM";

    // ButterKnife биндер для управения привязками к view-элементам
    private Unbinder unbinder;

    @BindView(R.id.generate_btn)
    Button button;
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
    @BindView(R.id.include)
    CardView cardView;
    @BindView(R.id.movie_img)
    ImageView poster;
    @BindView(R.id.spinner)
    MaterialSpinner materialSpinner;
    @BindView(R.id.spin_kit)
    ProgressBar progressBar;
    @BindView(R.id.includeProgressBar)
    LinearLayout layout;
    @BindView(R.id.container)
    ConstraintLayout constraintLayout;

    private GeneratePresenter presenter;
    private ArrayList<Genre> genres;
    private Film film;
    private boolean saved;


    public GenerateFragment() {
        // Required empty public constructor
        if (genres == null){
            genres = new ArrayList<>();
            genres.add(new Genre(RetrofitUtils.RANDOM_FILM, "Random"));
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new GeneratePresenter();
        presenter.setGenerateFragment(this);
        if (savedInstanceState!=null){
            if (savedInstanceState.getParcelableArrayList(SPINNER_LIST)!=null)
                this.genres = savedInstanceState.getParcelableArrayList(SPINNER_LIST);
            if (savedInstanceState.getParcelable(FILM)!=null)
                this.film = savedInstanceState.getParcelable(FILM);
            saved = true;
        }else{
            saved = false;
            presenter.initSpinner();
            presenter.adviceFilm(genres.get(0));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.generate_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        progressBar.setIndeterminateDrawable(new CubeGrid());
        if (saved){
            materialSpinner.setItems(toStringList(genres));
            // TODO: 20.04.2017  Fix java.lang.IllegalArgumentException: At least one dimension has to be positive number in setFilmCard(film) as poster.getMeasuredWidth() == 0 && poster.getMeasuredHeight() == 0
            setFilmCard(film,saved);
        }
        button.setOnClickListener(v -> {
            presenter.adviceFilm(genres.get(materialSpinner.getSelectedIndex()));
            getProgressBar(constraintLayout,layout);
        });
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter = null;
    }

    @Override
    public void showMovie(Film film) {
        Log.i(TAG, "showMovie: " + film.toString());
        this.film = film;
        setFilmCard(film,saved);
    }

    @Override
    public void showError(Throwable throwable) {
        getHost(constraintLayout,layout);
        if (throwable.getMessage().startsWith(RetrofitUtils.UNKNOW_HOST_EXEPTION))
            DialogFactory.newInstance(R.string.error,R.string.dialog_network_error)
                    .show(getFragmentManager(),DialogFactory.DIALOG_ERROR);
        if (throwable.getMessage().startsWith(RetrofitUtils.BAD_REQUEST))
            DialogFactory.newInstance(R.string.error,R.string.dialog_unknown_error)
                    .show(getFragmentManager(),DialogFactory.DIALOG_ERROR);
    }

    @Override
    public void addGenre(List<Genre> list) {
        genres.addAll(list);
        materialSpinner.setItems(toStringList(genres));
    }

    private void setFilmCard (Film film,boolean saved){
        if (!saved){
        Picasso.with(getContext())
                .load(RetrofitUtils.BASE_PATH_POSTER + film.getPosterPath())
                .centerCrop()
                .resize(poster.getMeasuredWidth(), poster.getMeasuredHeight())
                .into(poster);
        } else {
            Picasso.with(getContext())
                    .load(RetrofitUtils.BASE_PATH_POSTER + film.getPosterPath())
                    .into(poster);
        }
        originalTitle.setText(film.getOriginalTitle());
        title.setText(film.getTitle());
        description.setText(film.getOverview());
        year.setText(film.getReleaseDate().substring(0,4));
        getHost(constraintLayout,layout);
    }

    private List<String> toStringList (List <Genre> genres){
        List<String> strings = new ArrayList<>();
        Observable.fromIterable(genres)
                .map(Genre::getName)
                .map(s -> s.substring(0,1).toUpperCase()+s.substring(1,s.length()))
                .subscribe(strings::add);
        return strings;
    }

    private void getProgressBar (ConstraintLayout constraintLayout, LinearLayout linearLayout){
        constraintLayout.setVisibility(View.GONE);
        linearLayout.setVisibility(View.VISIBLE);
    }
    private void getHost (ConstraintLayout constraintLayout, LinearLayout linearLayout){
        constraintLayout.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (genres!=null)
        outState.putParcelableArrayList(SPINNER_LIST,genres);
        if (film!=null)
        outState.putParcelable(FILM,film);
    }
}
