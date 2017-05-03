package com.gmail.nf.project.jddca.noticefilm.model.rest;


import com.gmail.nf.project.jddca.noticefilm.model.pojos.UpcomingMovie;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UpcomingMovieService {

    @GET("movie/upcoming")
    Observable<UpcomingMovie> getUpcomingMovie(@Query("key") String key, @Query("lang") String lang);

}
