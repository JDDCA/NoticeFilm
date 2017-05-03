package com.gmail.nf.project.jddca.noticefilm.model.rest;



import com.gmail.nf.project.jddca.noticefilm.model.pojos.Genres;
import com.gmail.nf.project.jddca.noticefilm.model.pojos.PageMovieForGenre;

import io.reactivex.Observable;
import lombok.experimental.PackagePrivate;
import retrofit2.Retrofit;
import retrofit2.http.Path;
import retrofit2.http.Query;

@PackagePrivate
public class GenerateMovieServiceImpl implements GenerateMovieService{

    private Retrofit retrofit;

    public GenerateMovieServiceImpl(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public Observable<Genres> getGenres(@Query("api_key") String key, @Query("language") String language) {
        GenerateMovieService service = retrofit.create(GenerateMovieService.class);
        return service.getGenres(key, language);
    }

    @Override
    public Observable<PageMovieForGenre> getPages(@Path("genre_id") String genreId, @Query("api_key") String key, @Query("language") String language) {
        GenerateMovieService service = retrofit.create(GenerateMovieService.class);
        return service.getPages(genreId, key, language);
    }

    @Override
    public Observable<PageMovieForGenre> getPage(@Path("genre_id") String genreId, @Query("api_key") String key,
                                                 @Query("language") String language,@Query("include_adult") String adult ,@Query("page") int pageInt) {
        GenerateMovieService service = retrofit.create(GenerateMovieService.class);
        return service.getPage(genreId, key, language, adult, pageInt);
    }

}
