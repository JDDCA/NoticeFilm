package com.gmail.nf.project.jddca.noticefilm.view.fragment.generate;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.ybq.android.spinkit.style.CubeGrid;
import com.gmail.nf.project.jddca.noticefilm.R;
import com.gmail.nf.project.jddca.noticefilm.model.pojos.Film;
import com.gmail.nf.project.jddca.noticefilm.model.utils.DialogFactory;
import com.gmail.nf.project.jddca.noticefilm.model.utils.RetrofitService;
import com.gmail.nf.project.jddca.noticefilm.presenter.generate.GeneratePresenterImpl;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.context.ContextFragmentImpl;
import com.gmail.nf.project.jddca.noticefilm.presenter.generate.GeneratePresenter;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import lombok.experimental.PackagePrivate;

/**
 * A simple {@link Fragment} subclass.
 */
@PackagePrivate
public class GenerateFragmentImpl extends ContextFragmentImpl implements GenerateFragment {

    private final String TAG = getClass().getSimpleName();

    private Unbinder unbinder;
    private GeneratePresenter presenter;

    @BindView(R.id.includeProgressBar)
    LinearLayout progressBarLayout;
    @BindView(R.id.default_container)
    ConstraintLayout defaultLayout;
    @BindView(R.id.spin_kit)
    ProgressBar progressBar;
    @BindView(R.id.spinner)
    MaterialSpinner materialSpinner;
    @BindView(R.id.generate_btn)
    FloatingActionButton generateBtn;
    @BindView(R.id.movie_img)
    ImageView poster;
    @BindView(R.id.movie_title)
    TextView title;
    @BindView(R.id.movie_year_realese)
    TextView year;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (savedInstanceState != null) {
//            presenter = savedInstanceState.getParcelable(GeneratePresenterImpl.PARCELABLE_GENERATE_PRESENTER);
//            presenter.updateRefFragment(this);
//            presenter.onCreate();
//        } else {
//            presenter = new GeneratePresenterImpl(this);
//            presenter.onCreate();
//        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.generate_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        if (savedInstanceState != null) {
            presenter = savedInstanceState.getParcelable(GeneratePresenterImpl.PARCELABLE_GENERATE_PRESENTER);
            presenter.updateRefFragment(this);
            presenter.onCreate();
        } else {
            presenter = new GeneratePresenterImpl(this);
            presenter.onCreate();
        }
        progressBar.setIndeterminateDrawable(new CubeGrid());
        materialSpinner.setItems(presenter.loadGenres());
        if (presenter.loadFilm() != null) {
            showOldFilm(presenter.loadFilm());
        }
        generateBtn.setOnClickListener(v -> presenter.downloadFilm(materialSpinner.getSelectedIndex()));
        presenter.onCreatedView();

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(GeneratePresenterImpl.PARCELABLE_GENERATE_PRESENTER, presenter);
    }


    @Override
    public void showFilm(Film film) {
        title.setText(film.getTitle());
        if (film.getReleaseDate() != null)
            year.setText(film.getReleaseDate().substring(0, 4));
        Picasso.with(getContext())
                .load(RetrofitService.BASE_PATH_POSTER + film.getPosterPath())
                .centerCrop()
                .resize(poster.getMeasuredWidth(), poster.getMeasuredHeight())
                .into(poster, new Callback() {
                    @Override
                    public void onSuccess() {
                        presenter.onStop();
                    }
                    @Override
                    public void onError() {
                        Picasso.with(getContext())
                                .load(R.drawable.lock_stock)
                                .centerCrop()
                                .resize(poster.getMeasuredWidth(), poster.getMeasuredHeight())
                                .into(poster, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                        presenter.onStop();
                                    }
                                    @Override
                                    public void onError() {
                                        presenter.onStop();
                                    }
                                });}});
    }

    private void showOldFilm(Film film) {
        title.setText(film.getTitle());
        if (film.getReleaseDate() != null)
            year.setText(film.getReleaseDate().substring(0, 4));
        Picasso.with(getContext())
                .load(RetrofitService.BASE_PATH_POSTER + film.getPosterPath())
                .into(poster, new Callback() {
                    @Override
                    public void onSuccess() {
                        presenter.onStop();
                    }
                    @Override
                    public void onError() {
                        Picasso.with(getContext())
                                .load(R.drawable.lock_stock)
                                .into(poster, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                        presenter.onStop();
                                    }
                                    @Override
                                    public void onError() {
                                        presenter.onStop();
                                    }
                                });}});
    }

    @Override
    public void showError(Throwable throwable) {
        Log.e(TAG, "showError: ", throwable);
        presenter.onStop();
        if (throwable.getMessage().startsWith(RetrofitService.UNKNOW_HOST_EXEPTION)||throwable.getMessage().startsWith("I have not internet "))
            DialogFactory.newInstance(R.string.error, R.string.dialog_network_error)
                    .show(getFragmentManager(), DialogFactory.DIALOG_ERROR);
    }

    @Override
    public void toLog(String s) {
        Log.i(TAG, "toLog: " + s);
    }

    @Override
    public void updateGenres(List<String> stringList) {
        if (materialSpinner != null)
            materialSpinner.setItems(stringList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter = null;
    }

    @Override
    public Fragment getFrgmnt() {
        return this;
    }

    @Override
    public View getProgressBarView() {
        return progressBarLayout;
    }

    @Override
    public View getDefaultView() {
        return defaultLayout;
    }

}
