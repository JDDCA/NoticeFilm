package com.gmail.nf.project.jddca.noticefilm.model.rest;



import com.gmail.nf.project.jddca.noticefilm.model.pojos.Genres;
import com.gmail.nf.project.jddca.noticefilm.model.pojos.PageMovieForGenre;
import com.gmail.nf.project.jddca.noticefilm.model.utils.RetrofitUtils;

import io.reactivex.Observable;
import lombok.experimental.PackagePrivate;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
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
                                                 @Query("language") String language, @Query("page") int pageInt) {
        GenerateMovieService service = retrofit.create(GenerateMovieService.class);
        return service.getPage(genreId, key, language, pageInt);
    }

}
