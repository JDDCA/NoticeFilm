package com.gmail.nf.project.jddca.noticefilm.view.fragment.generate;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.style.CubeGrid;
import com.gmail.nf.project.jddca.noticefilm.R;
import com.gmail.nf.project.jddca.noticefilm.model.pojos.Film;
import com.gmail.nf.project.jddca.noticefilm.presenter.generate.GeneratePresenterImpl;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.context.ContextFragmentImpl;
import com.gmail.nf.project.jddca.noticefilm.presenter.generate.GeneratePresenter;
import com.jaredrummler.materialspinner.MaterialSpinner;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
        if (savedInstanceState != null) {
            presenter = savedInstanceState.getParcelable(GeneratePresenterImpl.PARCELABLE_GENERATE_PRESENTER);
            presenter.onCreate();
        }else {
            presenter = new GeneratePresenterImpl(this);
            presenter.onCreate();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.generate_fragment, container, false);
        Log.i(TAG, "onCreateView: ");
        unbinder = ButterKnife.bind(this, view);
        progressBar.setIndeterminateDrawable(new CubeGrid());
        materialSpinner.setItems(presenter.loadGenres());
        return view;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(GeneratePresenterImpl.PARCELABLE_GENERATE_PRESENTER, presenter);
    }

    @Override
    public void showFilm(Film film) {

    }

    @Override
    public void showError(Throwable throwable) {

    }

    @Override
    public void toLog(String s) {
        Log.i(TAG, "toLog: " + s);
    }

    @Override
    public void updateGenres(List<String> stringList) {
        Log.i(TAG, "updateGenres: ");
        if (materialSpinner != null)
            materialSpinner.setItems(stringList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
