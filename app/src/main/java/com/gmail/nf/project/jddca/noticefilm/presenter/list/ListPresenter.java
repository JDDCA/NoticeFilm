package com.gmail.nf.project.jddca.noticefilm.presenter.list;


import com.gmail.nf.project.jddca.noticefilm.presenter.base.BasePresenter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public interface ListPresenter extends BasePresenter {
    DatabaseReference getRefFav();
}
