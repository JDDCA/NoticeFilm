package com.gmail.nf.project.jddca.noticefilm.view.fragment.list;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.gmail.nf.project.jddca.noticefilm.R;
import com.gmail.nf.project.jddca.noticefilm.model.pojos.Film;
import com.gmail.nf.project.jddca.noticefilm.presenter.list.ListPresenter;
import com.gmail.nf.project.jddca.noticefilm.presenter.list.ListPresenterImpl;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.context.ContextFragmentImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ListFragmentImpl extends ContextFragmentImpl implements ListFragment{

    public static final String LIST_TAG = "list_tag";

    private ListPresenter presenter;
    private Unbinder unbinder;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public ListFragmentImpl() {
        this.presenter = new ListPresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(
                new FirebaseRecyclerAdapter<Film, ListHolder>(Film.class, R.layout.item_film, ListHolder.class, presenter.getRefFav()) {
            @Override
            protected void populateViewHolder(ListHolder viewHolder, Film model, int position) {
                viewHolder.bindFilm(model);
            }
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
    public void saveFavorites() {

    }

    @Override
    public void saveList() {

    }

    @Override
    public Fragment getFrgmnt() {
        return this;
    }
}
