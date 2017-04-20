package com.gmail.nf.project.jddca.noticefilm.presenter;

import android.content.Context;
import android.os.Build;
import android.os.LocaleList;
import android.util.Log;

import com.gmail.nf.project.jddca.noticefilm.R;
import com.gmail.nf.project.jddca.noticefilm.model.DataManager;
import com.gmail.nf.project.jddca.noticefilm.model.pojos.Genre;
import com.gmail.nf.project.jddca.noticefilm.model.utils.RetrofitUtils;
import com.gmail.nf.project.jddca.noticefilm.view.MovieViewGenerate;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.GenerateFragment;

import lombok.Setter;
import lombok.experimental.PackagePrivate;

@PackagePrivate
public class GeneratePresenter {

    private final String TAG = getClass().getSimpleName();

    private DataManager dm;
    @Setter private GenerateFragment generateFragment;

    public GeneratePresenter() {
        dm = DataManager.getInstance();
    }

    public void initSpinner() {
                dm.initGenres(generateFragment,generateFragment.getContext());
    }

    public void adviceFilm(Genre genre) {
        if (genre.getId()!=RetrofitUtils.RANDOM_FILM){
            dm.getFilm(generateFragment,generateFragment.getContext(),genre.getId());
        }else {
            dm.getRandomFilm(generateFragment,generateFragment.getContext());
        }
    }
}
