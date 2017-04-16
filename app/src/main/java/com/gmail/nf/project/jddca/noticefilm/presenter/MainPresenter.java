package com.gmail.nf.project.jddca.noticefilm.presenter;

import com.gmail.nf.project.jddca.noticefilm.model.DataManager;
import com.gmail.nf.project.jddca.noticefilm.model.pojos.Movie;
import com.gmail.nf.project.jddca.noticefilm.model.rest.MovieService;
import com.gmail.nf.project.jddca.noticefilm.model.rest.MovieServiceImpl;
import com.gmail.nf.project.jddca.noticefilm.view.MovieView;
import com.gmail.nf.project.jddca.noticefilm.view.activity.MainActivity;

import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import lombok.Setter;
import lombok.experimental.PackagePrivate;

@PackagePrivate
public class MainPresenter {

    private final String TAG = getClass().getName();

    private MovieView movieView;

    private DataManager dataManager;

    private MovieService movieService;

    @Setter
    private MainActivity activity;

    public MainPresenter(MovieView movieView, MovieService movieService) {
        dataManager = DataManager.getInstance();
        this.movieView = movieView;
        this.movieService = movieService;
    }
//
//    public MainPresenter() {
//        this.movieService = new MovieServiceImpl();
//        dataManager = DataManager.getInstance();
//    }

    public void logout(int PROVIDER) {
        dataManager.logout(activity, PROVIDER);
    }

    public void getMovie(int id) {
        Observable<Movie> movieObservable = movieService.get(id);
        movieObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Movie>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Movie movie) {
                        movieView.showMovie(movie);
                    }

                    @Override
                    public void onError(Throwable e) {
                        movieView.showError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    // Генерирует рандомный ID
    public int getRandomID() {
        Random random = new Random();
        return random.nextInt(500);
    }

}

