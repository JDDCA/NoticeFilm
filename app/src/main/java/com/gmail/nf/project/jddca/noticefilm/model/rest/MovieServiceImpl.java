package com.gmail.nf.project.jddca.noticefilm.model.rest;


import com.gmail.nf.project.jddca.noticefilm.model.pojos.Movie;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;

public class MovieServiceImpl implements MovieService {

    private static final String KEY = "e155ee391fe67e4bced2832115371e0c";
    private static final String URL = "https://api.themoviedb.org/3/";

    @Override
    public Observable<Movie> get(@Path("movie_id") long id, @Path("api_key") String key) {

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .build();

        MovieService movieService = retrofit.create(MovieService.class);

        return movieService.get(id, key);
    }
}
