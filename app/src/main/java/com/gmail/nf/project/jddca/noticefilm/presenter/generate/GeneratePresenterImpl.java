package com.gmail.nf.project.jddca.noticefilm.presenter.generate;


import android.accounts.NetworkErrorException;
import android.content.Context;
import android.view.View;

import com.gmail.nf.project.jddca.noticefilm.model.pojos.Film;
import com.gmail.nf.project.jddca.noticefilm.model.pojos.Genre;
import com.gmail.nf.project.jddca.noticefilm.model.pojos.Genres;
import com.gmail.nf.project.jddca.noticefilm.model.rest.GenerateMovieService;
import com.gmail.nf.project.jddca.noticefilm.model.rest.GenerateMovieServiceImpl;
import com.gmail.nf.project.jddca.noticefilm.model.utils.ApiService;
import com.gmail.nf.project.jddca.noticefilm.model.utils.FirebaseService;
import com.gmail.nf.project.jddca.noticefilm.model.utils.RetrofitService;
import com.gmail.nf.project.jddca.noticefilm.presenter.base.BasePresenterImpl;
import com.gmail.nf.project.jddca.noticefilm.view.activity.LoginActivity;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.generate.GenerateFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GeneratePresenterImpl extends BasePresenterImpl implements GeneratePresenter {

    private GenerateFragment f;
    private FirebaseService fs;
    private GenerateMovieService gms;
    private List<Genre> genres;
    private Film film;

    public GeneratePresenterImpl(GenerateFragment fragment) {
        this.f = fragment;
        gms = new GenerateMovieServiceImpl(RetrofitService.getRetrofit());
        fs = new FirebaseService();
    }


    @Override
    public void onCreate() {
//        if (!FirebaseService.checkSession()) {
//            f.startActvt(LoginActivity.createIntent(f.getActvt()));
//            f.getActvt().finish();
//        }
        showProgressBar();
        if (!checkNetwork(f.getCntxt())) {
            f.showError(new NetworkErrorException("I have not internet connection"));
        } else {
            if (genres == null)
                initGenres();
            downloadFilm(RetrofitService.FIRST);
        }
    }


    @Override
    public void downloadFilm(int selectedIndex) {
        showProgressBar();
        if (!checkNetwork(f.getCntxt())) {
            f.showError(new NetworkErrorException("I have not internet connection"));
        } else {
            if (selectedIndex != RetrofitService.FIRST) {
                downloadGenreFilm(selectedIndex);
            } else {
                downloadRandomFilm();
            }
        }
    }

    @Override
    public void movieToFav() {
        checkAuthUser();
    }

    @Override
    public void movieToList() {
        checkAuthUser();
    }

    private void downloadGenreFilm(int selectedIndex) {
        String key = ApiService.getApiKey(f.getCntxt());
        String locale = ApiService.getLocales(f.getCntxt());
        String id = Integer.toString(RetrofitService.RANDOM_FILM);
        if (genres != null)
            id = Integer.toString(genres.get(selectedIndex).getId());
        String finalId = id;
//        f.toLog("Genre id: " + id);
//        f.toLog("FinalId id: " + id);
        gms.getPages(finalId, key, locale)
                .subscribeOn(Schedulers.io())
                .take(1)
                .subscribe(pMFG -> {
//                    f.toLog(Integer.toString(pMFG.getTotalPages()));
                    Random r = new Random(System.currentTimeMillis());
                    int page = 1;
                    if (pMFG.getTotalPages() > RetrofitService.MAX_PAGES) {
                        page = r.nextInt(RetrofitService.MAX_PAGES) + 1;
                    } else {
                        page = r.nextInt(pMFG.getTotalPages()) + 1;
                    }
                    gms.getPage(finalId, key, locale, RetrofitService.INCLUDE_ABULT, page)
                            .take(1)
                            .map(pageMovieForGenre -> pageMovieForGenre.getResults()
                                    .get(new Random(System.currentTimeMillis()).nextInt(pageMovieForGenre.getResults().size())))
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    film_r -> {
//                                        f.toLog(film_r.toString());
                                        this.film = film_r;
                                        f.showFilm(film_r);
                                    } , t -> f.showError(t));
                }, t -> f.showError(t));
    }

    private void downloadRandomFilm() {
//        f.toLog("downloadRandomFilm");
        String key = ApiService.getApiKey(f.getCntxt());
        String locale = ApiService.getLocales(f.getCntxt());
        gms.getGenres(key, locale)
                .subscribeOn(Schedulers.io())
                .take(1)
                .map(genres_film -> genres_film
                        .getGenres()
                        .get(new Random(System.currentTimeMillis()).nextInt(genres_film.getGenres().size())))
                .subscribe(genre -> {
//                    f.toLog(genre.toString());
                    gms.getPages(Integer.toString(genre.getId()), key, locale)
                            .take(1)
                            .subscribe(totalPage -> {
//                                f.toLog("Total pages:" + Integer.toString(totalPage.getTotalPages()));
                                Random r = new Random(System.currentTimeMillis());
                                int page = 1;
                                if (totalPage.getTotalPages() > RetrofitService.MAX_PAGES) {
                                    page = r.nextInt(RetrofitService.MAX_PAGES) + 1;
                                } else {
                                    page = r.nextInt(totalPage.getTotalPages()) + 1;
                                }
                                gms.getPage(Integer.toString(genre.getId()), key, locale, RetrofitService.INCLUDE_ABULT, page)
                                        .take(1)
                                        .map(pageMovieForGenre -> pageMovieForGenre.getResults()
                                                .get(new Random(System.currentTimeMillis()).nextInt(pageMovieForGenre.getResults().size())))
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(film_result -> {
//                                            f.toLog(film_result.toString());
                                            this.film = film_result;
                                            f.showFilm(film_result);
                                        }, throwable -> f.showError(throwable));
                            }, throwable -> f.showError(throwable));
                }, throwable -> f.showError(throwable));
    }

    private void initGenres() {
        genres = new ArrayList<>();
        genres.add(new Genre(RetrofitService.FIRST, "All genres"));
        downloadGenres();
    }

    private void downloadGenres() {
        if (!checkNetwork(f.getCntxt())) {
            f.showError(new NetworkErrorException("I have not internet connection"));
        }
        gms.getGenres(ApiService.getApiKey(f.getCntxt()), ApiService.getLocales(f.getCntxt()))
                .subscribeOn(Schedulers.io())
                .take(1)
                .map(Genres::getGenres)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::saveGenres, throwable -> f.showError(throwable));
    }

    private void saveGenres(List<Genre> v) {
        if (genres != null) {
            genres.addAll(v);
        } else {
            genres = new ArrayList<>();
            genres.add(new Genre(RetrofitService.FIRST, "All genres"));
            genres.addAll(v);
        }
        f.updateGenres(toStringList(genres));
    }

    @Override
    public void onStop() {
        hideProgressBar();
    }

    private List<String> toStringList(List<Genre> genres) {
        List<String> strings = new ArrayList<>();
        Observable.fromIterable(genres)
                .map(Genre::getName)
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1, s.length()))
                .subscribe(strings::add);
        return strings;
    }

    private boolean checkNetwork(Context context) {
        return ApiService.isNetwork(context);
    }

    private void checkAuthUser (){
        if (FirebaseService.isAnonymousUser())
            f.showAuthDialog();
    }

    private void hideProgressBar() {
        f.getProgressBarView().setVisibility(View.GONE);
        f.getDefaultView().setVisibility(View.VISIBLE);
    }

    private void showProgressBar() {
        f.getProgressBarView().setVisibility(View.VISIBLE);
        f.getDefaultView().setVisibility(View.GONE);
    }



}
