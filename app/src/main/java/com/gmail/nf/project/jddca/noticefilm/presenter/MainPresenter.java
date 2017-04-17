package com.gmail.nf.project.jddca.noticefilm.presenter;

import android.app.ProgressDialog;
import android.util.Log;

import com.gmail.nf.project.jddca.noticefilm.model.DataManager;
import com.gmail.nf.project.jddca.noticefilm.model.pojos.Movie;
import com.gmail.nf.project.jddca.noticefilm.model.rest.MovieService;
import com.gmail.nf.project.jddca.noticefilm.view.MovieView;
import com.gmail.nf.project.jddca.noticefilm.view.activity.MainActivity;

import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.PackagePrivate;

@PackagePrivate
public class MainPresenter {

    private final String TAG = getClass().getSimpleName();

    private MovieView movieView;

    private DataManager dataManager;

    private Disposable disposable;

    private MovieService movieService;

    private ProgressDialog progressDialog;

    @Setter
    @Getter
    private MainActivity activity;

    public MainPresenter(MovieView movieView, MovieService movieService) {
        dataManager = DataManager.getInstance();
        this.movieView = movieView;
        this.movieService = movieService;
    }

    public void logout(int PROVIDER) {
        dataManager.logout(activity, PROVIDER);
    }

    public void getMovie(String id, String key, String language) {

        progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Waiting is infinite :)");

        Observable<Movie> movieObservable = movieService.getMovie(id, key, language);
        movieObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Movie>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "Check isDisposed: " + d.isDisposed());
                        Log.d(TAG, "d.dispose(): " + d.isDisposed());
//                        if (!d.isDisposed()) {
//                            d.dispose();
//                            Log.d(TAG, "Start show Spinner progress dialog");
//                            progressDialog.show();
//                        }
                        progressDialog.show();
                        disposable = d;
                    }

                    @Override
                    public void onNext(Movie movie) {
                        Log.d(TAG, "Show movie method invocation");
                        movieView.showMovie(movie);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "Show error method invocation");
                        movieView.showError();
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {
                        if (progressDialog.isIndeterminate()) {
                            progressDialog.dismiss();
                        }
                        disposable.dispose();
                    }
                });
    }

    // Генерирует рандомный ID
    public int getRandomID() {
        Random random = new Random();
        return random.nextInt(500);
    }

}

