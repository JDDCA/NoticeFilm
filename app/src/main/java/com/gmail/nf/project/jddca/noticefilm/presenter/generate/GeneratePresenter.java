package com.gmail.nf.project.jddca.noticefilm.presenter.generate;


import android.os.Parcelable;

import com.gmail.nf.project.jddca.noticefilm.presenter.base.BasePresenter;

import java.util.List;

public interface GeneratePresenter extends BasePresenter, Parcelable {


    List <String> loadGenres();
}
