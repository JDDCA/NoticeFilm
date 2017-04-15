package com.gmail.nf.project.jddca.noticefilm.presenter;

import android.util.Log;

import com.gmail.nf.project.jddca.noticefilm.model.DataManager;
import com.gmail.nf.project.jddca.noticefilm.model.pojos.Movie;
import com.gmail.nf.project.jddca.noticefilm.model.rest.MovieService;
import com.gmail.nf.project.jddca.noticefilm.model.rest.MovieServiceImpl;
import com.gmail.nf.project.jddca.noticefilm.view.activity.MainActivity;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.MovieFragment;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import lombok.Setter;
import lombok.experimental.PackagePrivate;

@PackagePrivate
public class MainPresenter {

    DataManager dataManager;

    MovieService movieService = new MovieServiceImpl();

    @Setter
    MainActivity activity;

    public MainPresenter() {
        dataManager = DataManager.getInstance();
    }

    public void logout(int PROVIDER) {
        dataManager.logout(activity, PROVIDER);
    }

    public void getMovie(long id, String key) {

        Observable<Movie> movieObservable = movieService.get(id, key);

        movieObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Movie>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Movie movie) {
                Log.e("MainPresenter", movie.getTitle() + " " + movie.getOverview() + " " + movie.getReleaseDate());
            }

            @Override
            public void onError(Throwable e) {
                Log.e("MainPresenter", e.toString());
            }

            @Override
            public void onComplete() {
                Log.e("MainPresenter", "Success");
            }
        });

    }

}
