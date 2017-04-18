package com.gmail.nf.project.jddca.noticefilm.model.rest;


import com.gmail.nf.project.jddca.noticefilm.model.pojos.Movie;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    @GET("movie/{id}?api_key=&language=")
    Observable<Movie> getMovie(@Path("id") String id, @Query("api_key") String key, @Query("lang") String language);

}
