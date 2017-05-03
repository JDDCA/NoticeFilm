package com.gmail.nf.project.jddca.noticefilm.presenter.list;


import com.gmail.nf.project.jddca.noticefilm.model.db.DatabaseService;
import com.gmail.nf.project.jddca.noticefilm.model.db.DatabaseServiceImpl;
import com.gmail.nf.project.jddca.noticefilm.presenter.base.BasePresenterImpl;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.list.ListFragment;
import com.google.firebase.database.DatabaseReference;

public class ListPresenterImpl extends BasePresenterImpl implements ListPresenter {

    private ListFragment fragment;
    private DatabaseService databaseService;


    public ListPresenterImpl(ListFragment fragment) {
        this.fragment = fragment;
        databaseService = new DatabaseServiceImpl();
    }

    @Override
    public DatabaseReference getRefFav() {
        return databaseService.getRefList();
    }
}
