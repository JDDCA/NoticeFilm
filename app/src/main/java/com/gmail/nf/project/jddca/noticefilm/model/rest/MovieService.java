package com.gmail.nf.project.jddca.noticefilm.model.rest;


import com.gmail.nf.project.jddca.noticefilm.model.pojos.Movie;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovieService {

    @GET("movie/{movie_id}?api_key=e155ee391fe67e4bced2832115371e0c&language=ru-RU")
    Observable<Movie> get(@Path("movie_id") int id);

}
