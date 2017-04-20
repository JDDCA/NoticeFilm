package com.gmail.nf.project.jddca.noticefilm.model.rest;



import com.gmail.nf.project.jddca.noticefilm.model.pojos.Genres;
import com.gmail.nf.project.jddca.noticefilm.model.pojos.PageMovieForGenre;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GenerateMovieService {

    @GET ("genre/movie/list")
    Observable<Genres> getGenres (@Query("api_key") String key, @Query("language") String language);

    @GET ("genre/{genre_id}/movies")
    Observable <PageMovieForGenre> getPages (@Path("genre_id") String genreId, @Query("api_key") String key, @Query("language") String language);

    @GET ("genre/{genre_id}/movies")
    Observable <PageMovieForGenre> getPage (@Path("genre_id") String genreId, @Query("api_key") String key, @Query("language") String language, @Query("page") int pageInt);
}
