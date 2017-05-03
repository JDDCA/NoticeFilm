package com.gmail.nf.project.jddca.noticefilm.view.fragment.list;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.gmail.nf.project.jddca.noticefilm.R;
import com.gmail.nf.project.jddca.noticefilm.model.pojos.Film;
import com.gmail.nf.project.jddca.noticefilm.model.utils.DialogFactory;
import com.gmail.nf.project.jddca.noticefilm.model.utils.ExceptionService;
import com.gmail.nf.project.jddca.noticefilm.presenter.list.ListPresenter;
import com.gmail.nf.project.jddca.noticefilm.presenter.list.ListPresenterImpl;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.context.ContextFragmentImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ListFragmentImpl extends ContextFragmentImpl implements ListFragment {

    private final String TAG = getClass().getSimpleName();
    public static final String LIST_TAG = "list_tag";


    private ListPresenter presenter;
    private Unbinder unbinder;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.info_container)
    ConstraintLayout infoView;
    @BindView(R.id.emptyButton)
    Button emptyButton;

    public ListFragmentImpl() {
        this.presenter = new ListPresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(new FirebaseRecyclerAdapter<Film, ListHolder>(Film.class, R.layout.item_film, ListHolder.class, presenter.getRefList()) {
                @Override
                protected void populateViewHolder(ListHolder viewHolder, Film model, int position) {
                    viewHolder.bindFilm(model, presenter);
                }
            }
        );
        emptyButton.setOnClickListener(v -> presenter.transactionToGenrate());


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (presenter != null)
            presenter.onCreate();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter = null;
    }


    @Override
    public void showError(Throwable throwable) {
        if (throwable instanceof ExceptionService.NotAuthorizedException)
            DialogFactory.newInstance(R.string.info, R.string.auth_dialog)
                    .show(getFragmentManager(), DialogFactory.DIALOG_ERROR);
    }

    @Override
    public void toLog(String s) {
        Log.i(TAG, "toLog: "+s);
    }

    @Override
    public View getInfoPage() {
        return infoView;
    }

    @Override
    public View getBodyPage() {
        return recyclerView;
    }


    @Override
    public Fragment getFrgmnt() {
        return this;
    }
}
