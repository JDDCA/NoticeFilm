package com.gmail.nf.project.jddca.noticefilm.presenter.generate;


import android.accounts.NetworkErrorException;
import android.content.Context;
import android.view.View;

import com.gmail.nf.project.jddca.noticefilm.model.db.DatabaseService;
import com.gmail.nf.project.jddca.noticefilm.model.db.DatabaseServiceImpl;
import com.gmail.nf.project.jddca.noticefilm.model.pojos.Film;
import com.gmail.nf.project.jddca.noticefilm.model.pojos.Genre;
import com.gmail.nf.project.jddca.noticefilm.model.pojos.Genres;
import com.gmail.nf.project.jddca.noticefilm.model.rest.GenerateMovieService;
import com.gmail.nf.project.jddca.noticefilm.model.rest.GenerateMovieServiceImpl;
import com.gmail.nf.project.jddca.noticefilm.model.utils.ApiService;
import com.gmail.nf.project.jddca.noticefilm.model.utils.ExceptionService.NotAuthorizedException;
import com.gmail.nf.project.jddca.noticefilm.model.utils.FirebaseAuthService;
import com.gmail.nf.project.jddca.noticefilm.model.utils.RetrofitService;
import com.gmail.nf.project.jddca.noticefilm.presenter.base.BasePresenterImpl;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.generate.GenerateFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GeneratePresenterImpl extends BasePresenterImpl implements GeneratePresenter {

    private GenerateFragment generateFragment;
    private DatabaseService databaseService;
    private GenerateMovieService generateMovieService;
    private List<Genre> genres;
    private Film film;

    public GeneratePresenterImpl(GenerateFragment fragment) {
        this.generateFragment = fragment;
        generateMovieService = new GenerateMovieServiceImpl(RetrofitService.getRetrofit());
        databaseService = new DatabaseServiceImpl();
    }


    @Override
    public void onCreate() {
        showProgressBar();
        if (!checkNetwork(generateFragment.getCntxt())) {
            generateFragment.showError(new NetworkErrorException("I have not internet connection"));
        } else {
            if (genres == null)
                initGenres();
            downloadFilm(RetrofitService.FIRST);
        }
    }


    @Override
    public void downloadFilm(int selectedIndex) {
        showProgressBar();
        if (!checkNetwork(generateFragment.getCntxt())) {
            generateFragment.showError(new NetworkErrorException("I have not internet connection"));
        } else {
            if (selectedIndex != RetrofitService.FIRST) {
                downloadGenreFilm(selectedIndex);
            } else {
                downloadRandomFilm();
            }
        }
    }

    @Override
    public void movieToList(boolean b) {
        if (!FirebaseAuthService.isAnonymousUser()) {
            if (film != null) {
                if (b)
                    databaseService.saveToListMovie(film);
                else
                    databaseService.removeListsMovie(film);
            } else
                generateFragment.showError(new NullPointerException("Film is null. I can't save null in databaseService"));
        } else
            generateFragment.showError(new NotAuthorizedException());
    }

    private void checkInDatabase(Film film){
        // TODO: 02.05.2017 Интернет соеденение
        databaseService.checkInListMovie(film,b -> generateFragment.showFilm(film,b),generateFragment::showError);
    }

    private void downloadGenreFilm(int selectedIndex) {
        String key = ApiService.getApiKey(generateFragment.getCntxt());
        String locale = ApiService.getLocales(generateFragment.getCntxt());
        String id = Integer.toString(RetrofitService.RANDOM_FILM);
        if (genres != null)
            id = Integer.toString(genres.get(selectedIndex).getId());
        String finalId = id;
//        generateFragment.toLog("Genre id: " + id);
//        generateFragment.toLog("FinalId id: " + id);
        generateMovieService.getPages(finalId, key, locale)
                .subscribeOn(Schedulers.io())
                .take(1)
                .subscribe(pMFG -> {
//                    generateFragment.toLog(Integer.toString(pMFG.getTotalPages()));
                    Random r = new Random(System.currentTimeMillis());
                    int page = 1;
                    if (pMFG.getTotalPages() > RetrofitService.MAX_PAGES) {
                        page = r.nextInt(RetrofitService.MAX_PAGES) + 1;
                    } else {
                        page = r.nextInt(pMFG.getTotalPages()) + 1;
                    }
                    generateMovieService.getPage(finalId, key, locale, RetrofitService.INCLUDE_ABULT, page)
                            .take(1)
                            .map(pageMovieForGenre -> pageMovieForGenre.getResults()
                                    .get(new Random(System.currentTimeMillis()).nextInt(pageMovieForGenre.getResults().size())))
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    film_r -> {
//                                        generateFragment.toLog(film_r.toString());
                                        this.film = film_r;
                                        checkInDatabase(film);
//                                        generateFragment.showFilm(film_r);
                                    }, t -> generateFragment.showError(t));
                }, t -> generateFragment.showError(t));
    }

    private void downloadRandomFilm() {
//        generateFragment.toLog("downloadRandomFilm");
        String key = ApiService.getApiKey(generateFragment.getCntxt());
        String locale = ApiService.getLocales(generateFragment.getCntxt());
        generateMovieService.getGenres(key, locale)
                .subscribeOn(Schedulers.io())
                .take(1)
                .map(genres_film -> genres_film
                        .getGenres()
                        .get(new Random(System.currentTimeMillis()).nextInt(genres_film.getGenres().size())))
                .subscribe(genre -> {
//                    generateFragment.toLog(genre.toString());
                    generateMovieService.getPages(Integer.toString(genre.getId()), key, locale)
                            .take(1)
                            .subscribe(totalPage -> {
//                                generateFragment.toLog("Total pages:" + Integer.toString(totalPage.getTotalPages()));
                                Random r = new Random(System.currentTimeMillis());
                                int page = 1;
                                if (totalPage.getTotalPages() > RetrofitService.MAX_PAGES) {
                                    page = r.nextInt(RetrofitService.MAX_PAGES) + 1;
                                } else {
                                    page = r.nextInt(totalPage.getTotalPages()) + 1;
                                }
//                                generateFragment.toLog(page+" page");
//                                generateFragment.toLog(genre.getId()+" genreID");
                                generateMovieService.getPage(Integer.toString(genre.getId()), key, locale, RetrofitService.INCLUDE_ABULT, page)
//                                generateMovieService.getPage("10751", key, locale, RetrofitService.INCLUDE_ABULT, 516)
                                        .take(1)
                                        .map(pageMovieForGenre -> pageMovieForGenre.getResults()
                                                .get(new Random(System.currentTimeMillis()).nextInt(pageMovieForGenre.getResults().size())))
//                                        .map(pageMovieForGenre -> pageMovieForGenre.getResults().get(1))
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(film_result -> {
//                                            generateFragment.toLog(film_result.toString());
                                            this.film = film_result;
                                            checkInDatabase(film_result);
//                                            generateFragment.showFilm(film_result);
                                        }, throwable -> generateFragment.showError(throwable));
                            }, throwable -> generateFragment.showError(throwable));
                }, throwable -> generateFragment.showError(throwable));
    }

    private void initGenres() {
        genres = new ArrayList<>();
        genres.add(new Genre(RetrofitService.FIRST, "All genres"));
        downloadGenres();
    }

    private void downloadGenres() {
        if (!checkNetwork(generateFragment.getCntxt())) {
            generateFragment.showError(new NetworkErrorException("I have not internet connection"));
        }
        generateMovieService.getGenres(ApiService.getApiKey(generateFragment.getCntxt()), ApiService.getLocales(generateFragment.getCntxt()))
                .subscribeOn(Schedulers.io())
                .take(1)
                .map(Genres::getGenres)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::saveGenres, throwable -> generateFragment.showError(throwable));
    }

    private void saveGenres(List<Genre> v) {
        if (genres != null) {
            genres.addAll(v);
        } else {
            genres = new ArrayList<>();
            genres.add(new Genre(RetrofitService.FIRST, "All genres"));
            genres.addAll(v);
        }
        generateFragment.updateGenres(toStringList(genres));
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

    private void hideProgressBar() {
        generateFragment.getProgressBarView().setVisibility(View.GONE);
        generateFragment.getDefaultView().setVisibility(View.VISIBLE);
    }

    private void showProgressBar() {
        generateFragment.getProgressBarView().setVisibility(View.VISIBLE);
        generateFragment.getDefaultView().setVisibility(View.GONE);
    }


}
