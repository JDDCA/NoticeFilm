package com.gmail.nf.project.jddca.noticefilm.model.rest;


import com.gmail.nf.project.jddca.noticefilm.model.pojos.Movie;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class MovieServiceImpl implements MovieService {

    public static final String BASE_URL = "https://api.themoviedb.org/3/";

    @Override
    public Observable<Movie> getMovie(@Path("id") String id, @Query("api_key") String key, @Query("lang") String language) {

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        MovieService movieService = retrofit.create(MovieService.class);

        return movieService.getMovie(id, key, language);
    }
}
