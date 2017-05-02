package com.gmail.nf.project.jddca.noticefilm.presenter.list;


import com.gmail.nf.project.jddca.noticefilm.presenter.base.BasePresenterImpl;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.list.ListFragment;

public class ListPresenterImpl extends BasePresenterImpl implements ListPresenter {

    private ListFragment fragment;

    public ListPresenterImpl(ListFragment fragment) {
        this.fragment = fragment;
    }
}
