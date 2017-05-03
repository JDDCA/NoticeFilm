package com.gmail.nf.project.jddca.noticefilm.view.fragment.generate;


import android.accounts.NetworkErrorException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.ybq.android.spinkit.style.CubeGrid;
import com.gmail.nf.project.jddca.noticefilm.R;
import com.gmail.nf.project.jddca.noticefilm.model.pojos.Film;
import com.gmail.nf.project.jddca.noticefilm.model.utils.DialogFactory;
import com.gmail.nf.project.jddca.noticefilm.model.utils.ExceptionService.NotAuthorizedException;
import com.gmail.nf.project.jddca.noticefilm.model.utils.RetrofitService;
import com.gmail.nf.project.jddca.noticefilm.presenter.generate.GeneratePresenter;
import com.gmail.nf.project.jddca.noticefilm.presenter.generate.GeneratePresenterImpl;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.context.ContextFragmentImpl;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class GenerateFragmentImpl extends ContextFragmentImpl implements GenerateFragment {

    private final String TAG = getClass().getSimpleName();
    public static final String GENERATE_TAG = "generate_tag";


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
    @BindView(R.id.add_list)
    ImageButton addList;

    private boolean isTouchAddList = true;

    public GenerateFragmentImpl() {
        this.presenter = new GeneratePresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.generate_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        progressBar.setIndeterminateDrawable(new CubeGrid());
        generateBtn.setOnClickListener(v -> presenter.downloadFilm(materialSpinner.getSelectedIndex()));
        setButtonImage(addList,isTouchAddList,R.drawable.ic_playlist_add_black_24px,R.drawable.ic_playlist_add_check_black_24px);
        addList.setOnClickListener(v -> {
            if (isTouchAddList) {
                addList.setImageResource(R.drawable.ic_playlist_add_check_black_24px);
                presenter.movieToList(isTouchAddList);
            } else {
                addList.setImageResource(R.drawable.ic_playlist_add_black_24px);
                presenter.movieToList(isTouchAddList);
            }
            isTouchAddList = !isTouchAddList;
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (presenter!=null)
        presenter.onCreate();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter = null;
    }

    @Override
    public void updateGenres(List<String> stringList) {
        if (materialSpinner != null)
            materialSpinner.setItems(stringList);
    }

    @Override
    public void showError(Throwable throwable) {
        if (presenter!=null) {
            presenter.onStop();
            Log.e(TAG, "showError: ", throwable);
            if (throwable instanceof NetworkErrorException)
                DialogFactory.newInstance(R.string.error, R.string.dialog_network_error)
                        .show(getFragmentManager(), DialogFactory.DIALOG_ERROR);
            if (throwable instanceof NotAuthorizedException)
                DialogFactory.newInstance(R.string.info, R.string.auth_dialog)
                        .show(getFragmentManager(), DialogFactory.DIALOG_ERROR);
        }
    }

    public void setButtonImage (ImageButton imageButton,boolean b, int first_state, int second_state){
        imageButton.setImageResource(first_state);
        if (b)
            imageButton.setImageResource(second_state);
        else
            imageButton.setImageResource(first_state);
    }

    @Override
    public void showFilm(Film film, boolean bList) {
        isTouchAddList = !bList;
        if (addList!=null)
        setButtonImage(addList,bList,R.drawable.ic_playlist_add_black_24px,R.drawable.ic_playlist_add_check_black_24px);
        if (title!=null)
        title.setText(film.getTitle());
        if (film.getReleaseDate() != null)
            if (year!=null)
            year.setText(film.getReleaseDate().substring(0, 4));
        if (poster!=null)
        Picasso.with(getContext())
                .load(RetrofitService.BASE_PATH_POSTER + film.getPosterPath())
//                .centerCrop()
//                .resize(poster.getMeasuredWidth(), poster.getMeasuredHeight())
                .into(poster, new Callback() {
                    @Override
                    public void onSuccess() {
                        if (presenter!=null)
                        presenter.onStop();
                    }
                    @Override
                    public void onError() {
                        if (poster!=null)
                        Picasso.with(getContext())
                                .load(R.drawable.lock_stock)
//                                .centerCrop()
//                                .resize(poster.getMeasuredWidth(), poster.getMeasuredHeight())
                                .into(poster, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                        if (presenter!=null)
                                            presenter.onStop();
                                    }
                                    @Override
                                    public void onError() {
                                        showError(new IllegalAccessException("I can't loading default poster! Check me, please!"));
                                    }
                                });
                    }
                });
    }

    @Override
    public void toLog(String s) {
        Log.i(TAG, "toLog: " + s);
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
