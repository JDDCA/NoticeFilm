package com.gmail.nf.project.jddca.noticefilm.presenter;


import com.gmail.nf.project.jddca.noticefilm.model.pojos.UpcomingMovie;
import com.gmail.nf.project.jddca.noticefilm.model.rest.UpcomingMovieService;
import com.gmail.nf.project.jddca.noticefilm.model.rest.UpcomingMovieServiceImpl;
import com.gmail.nf.project.jddca.noticefilm.model.utils.ApiService;
import com.gmail.nf.project.jddca.noticefilm.model.utils.RetrofitService;
import com.gmail.nf.project.jddca.noticefilm.view.IView;
import com.gmail.nf.project.jddca.noticefilm.view.UpcomingView;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.UpcomingMovieFragment;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import lombok.Setter;

public class UpcomingMoviePresenter implements Presenter {

    @Setter
    private UpcomingMovieFragment fragment;

    private UpcomingView view;
    private UpcomingMovieService service;

    public UpcomingMoviePresenter(IView view) {
        service = new UpcomingMovieServiceImpl(RetrofitService.getRetrofit());
        this.view = (UpcomingView) view;
    }

    public void getUpcoming() {
        service.getUpcomingMovie(ApiService.getApiKey(fragment.getContext()), ApiService.getLocales(fragment.getContext()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(UpcomingMovie::getResults)
                .subscribe(view::showUpcomingMoviesList, view::showError);
    }
}
