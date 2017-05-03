package com.gmail.nf.project.jddca.noticefilm.model.rest;


import com.gmail.nf.project.jddca.noticefilm.model.pojos.UpcomingMovie;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.http.Query;

public class UpcomingMovieServiceImpl implements UpcomingMovieService {

    private Retrofit retrofit;

    public UpcomingMovieServiceImpl(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public Observable<List<UpcomingMovie>> getUpcomingMovie(@Query("key") String key, @Query("lang") String lang, @Query("page") Integer page) {
        UpcomingMovieService service = retrofit.create(UpcomingMovieService.class);
        return service.getUpcomingMovie(key, lang, page);
    }
}
