package com.gmail.nf.project.jddca.noticefilm.presenter.generate;


import android.accounts.NetworkErrorException;
import android.content.Context;
import android.os.Parcel;
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
import com.gmail.nf.project.jddca.noticefilm.view.fragment.generate.GenerateFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import lombok.Setter;

public class GeneratePresenterImpl extends BasePresenterImpl implements GeneratePresenter {

    public static final String PARCELABLE_GENERATE_PRESENTER = "com.gmail.nf.project.jddca.noticefilm.presenter.generate.PARCELABLE_GENERATE_PRESENTER";

    private GenerateFragment f;
    private FirebaseService fs;
    private GenerateMovieService gms;
    private List<Genre> genres;
    private Film film;
    private boolean viewCreated = false;


    public GeneratePresenterImpl(GenerateFragment fragment) {
        this.f = fragment;
        gms = new GenerateMovieServiceImpl(RetrofitService.getRetrofit());
        fs = new FirebaseService();
    }

    @Override
    public void onCreate() {
        if (genres != null) {
            f.updateGenres(toStringList(genres));
        } else {
            genres = new ArrayList<>();
            genres.add(RetrofitService.FIRST, new Genre(RetrofitService.RANDOM_FILM, "All genres"));
            if (!checkNetwork(f.getCntxt())) {
                f.showError(new NetworkErrorException("I have not internet connection"));
            } else {
                downloadGenres(genres);
                downloadFilm(RetrofitService.FIRST);
            }

        }
    }

    private void downloadGenres(List<Genre> genres) {
//        f.toLog("downloadGenres!");
        gms.getGenres(ApiService.getApiKey(f.getCntxt()), ApiService.getLocales(f.getCntxt()))
                .subscribeOn(Schedulers.io())
                .take(1)
                .map(Genres::getGenres)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(v -> {
                    genres.addAll(v);
                    f.updateGenres(toStringList(genres));
                }, throwable -> f.showError(throwable));
    }


    @Override
    public void downloadFilm(int selectedIndex) {
        if (viewCreated)
            showProgressBar();
        if (!checkNetwork(f.getCntxt())) {
            f.showError(new NetworkErrorException("I have not internet connection"));
        }else {
            if (genres.size()<=1)
                downloadGenres(genres);
            if (selectedIndex != RetrofitService.FIRST) {
                downloadGenreFilm(selectedIndex);
            } else {
                downloadRandomFilm();
            }
        }
    }

    private void downloadGenreFilm(int selectedIndex) {
        f.toLog("downloadGenreFilm");
        String key = ApiService.getApiKey(f.getCntxt());
        String locale = ApiService.getLocales(f.getCntxt());
        Integer id = genres.get(selectedIndex).getId();
//        gms.getPages(Integer.toString(id),key,locale)
//                .subscribeOn(Schedulers.io())
//                .take(1)

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
                                            f.toLog(film_result.toString());
                                            this.film = film_result;
                                            f.showFilm(film_result);
                                        }, throwable -> f.showError(throwable));
                            }, throwable -> f.showError(throwable));
                }, throwable -> f.showError(throwable));
    }


    private List<String> toStringList(List<Genre> genres) {
        List<String> strings = new ArrayList<>();
        Observable.fromIterable(genres)
                .map(Genre::getName)
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1, s.length()))
                .subscribe(strings::add);
        return strings;
    }

    @Override
    public List<String> loadGenres() {
        return toStringList(genres);
    }

    @Override
    public Film loadFilm() {
        return this.film;
    }

    @Override
    public void onStop() {
        hideProgressBar();
    }

    @Override
    public void onCreatedView() {
        this.viewCreated = true;
    }

    @Override
    public void updateRefFragment(GenerateFragment generateFragment) {
        this.f = generateFragment;
    }

    private boolean checkNetwork(Context context) {
        return ApiService.isNetwork(context);
    }

    private void hideProgressBar() {
        f.getProgressBarView().setVisibility(View.GONE);
        f.getDefaultView().setVisibility(View.VISIBLE);
    }

    private void showProgressBar() {
        f.getProgressBarView().setVisibility(View.VISIBLE);
        f.getDefaultView().setVisibility(View.GONE);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.genres);
        dest.writeParcelable(this.film, flags);
    }

    protected GeneratePresenterImpl(Parcel in) {
        this.genres = in.createTypedArrayList(Genre.CREATOR);
        this.film = in.readParcelable(Film.class.getClassLoader());
    }

    public static final Creator<GeneratePresenterImpl> CREATOR = new Creator<GeneratePresenterImpl>() {
        @Override
        public GeneratePresenterImpl createFromParcel(Parcel source) {
            return new GeneratePresenterImpl(source);
        }

        @Override
        public GeneratePresenterImpl[] newArray(int size) {
            return new GeneratePresenterImpl[size];
        }
    };
}
