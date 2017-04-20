package com.gmail.nf.project.jddca.noticefilm.model.utils;


import android.content.Context;
import android.util.Log;

import com.gmail.nf.project.jddca.noticefilm.model.pojos.Genres;
import com.gmail.nf.project.jddca.noticefilm.model.rest.GenerateMovieService;
import com.gmail.nf.project.jddca.noticefilm.model.rest.GenerateMovieServiceImpl;
import com.gmail.nf.project.jddca.noticefilm.view.MovieView;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.GenerateFragment;

import java.util.Collection;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {

    public final static int RANDOM_FILM = -1;
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String BASE_PATH_POSTER = "https://image.tmdb.org/t/p/w500";
    public static final String UNKNOW_HOST_EXEPTION = "Unable to resolve host";
    public static final String BAD_REQUEST = "HTTP 400";



    private Retrofit getRetrofit (){
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    public void downloadRandomFilm(MovieView view, String key, String locale) {
        GenerateMovieService service = new GenerateMovieServiceImpl(getRetrofit());
        service.getGenres(key, locale)
                .subscribeOn(Schedulers.io()).take(1)
                .map(genres -> genres.getGenres().get(new Random().nextInt(genres.getGenres().size())))
                .subscribe(
                        genre -> service.getPages(Integer.toString(genre.getId()), key, locale).take(1)
                                .subscribe(
                                        pages -> service.getPage(Integer.toString(genre.getId()),key, locale,
                                                new Random().nextInt(pages.getTotalPages())).take(1)
                                                .map(pageMovieForGenre -> pageMovieForGenre.getResults()
                                                        .get(new Random().nextInt(pageMovieForGenre.getResults().size())))
                                                .observeOn(AndroidSchedulers.mainThread())
                                                .subscribe(view::showMovie , view::showError)
                                        , view::showError)
                        , view::showError);
    }

//    public void downloadGenreFilm(MovieView view, String key, String locale, Integer id) {
//        GenerateMovieService service = new GenerateMovieServiceImpl(getRetrofit());
//        service.getPages(Integer.toString(id),key,locale)
//                .subscribeOn(Schedulers.io()).take(1)
//                .subscribe(
//                        pages -> service.getPage(Integer.toString(id),key, locale,
//                                new Random().nextInt(pages.getTotalPages())).take(1)
//                                .map(pageMovieForGenre -> pageMovieForGenre.getResults()
//                                        .get(new Random().nextInt(pageMovieForGenre.getResults().size())))
//                                .observeOn(AndroidSchedulers.mainThread())
//                                .subscribe(view::showMovie , view::showError)
//                        , view::showError);
//    }
    public void downloadGenreFilm(MovieView view, String key, String locale, Integer id) {
        GenerateMovieService service = new GenerateMovieServiceImpl(getRetrofit());
        service.getPages(Integer.toString(id),key,locale)
                .subscribeOn(Schedulers.io()).take(1)
                .subscribe(
                        pages -> service.getPage(Integer.toString(id),key, locale,
                                new Random().nextInt(pages.getTotalPages())).take(1)
                                .map(pageMovieForGenre -> pageMovieForGenre.getResults()
                                        .get(new Random().nextInt(pageMovieForGenre.getResults().size())))
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(view::showMovie , view::showError)
                        , view::showError);
    }

    public void initGenreForSpinner(GenerateFragment view, String key, String locale) {
        GenerateMovieService service = new GenerateMovieServiceImpl(getRetrofit());
        service.getGenres(key, locale)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .take(1)
                .map(Genres::getGenres)
                .subscribe(view::addGenre, view::showError);
    }
}
