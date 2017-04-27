package com.gmail.nf.project.jddca.noticefilm.presenter.generate;


import android.os.Parcelable;

import com.gmail.nf.project.jddca.noticefilm.model.pojos.Film;
import com.gmail.nf.project.jddca.noticefilm.presenter.base.BasePresenter;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.generate.GenerateFragment;

import java.util.List;

public interface GeneratePresenter extends BasePresenter, Parcelable {


    List <String> loadGenres();
    Film loadFilm();
    void downloadFilm(int selectedIndex);
    void updateRefFragment(GenerateFragment generateFragment);
}
