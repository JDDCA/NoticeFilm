package com.gmail.nf.project.jddca.noticefilm.model.RetrofitZapr;



import com.gmail.nf.project.jddca.noticefilm.model.POJOS.REST.FilmsNew.ExampleNewF;
import com.gmail.nf.project.jddca.noticefilm.model.POJOS.REST.FilmsRandom.ExampleJyrnal;
import com.gmail.nf.project.jddca.noticefilm.model.POJOS.REST.Genre_of_films.ExampleJanr;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by bigi on 06.04.2017.
 */

public interface InterfFilm {

//    @GET("movie/{movie_id}?api_key=ba8e8a114ce7fc27aa71ebec8c0b1afe&language=ru-RU")
//    Call<Example> listRepos(@Path("movie_id") int groupId);

    @GET("movie/upcoming?api_key=ba8e8a114ce7fc27aa71ebec8c0b1afe&language=ru-RU&")
    Call<ExampleNewF> getJSON(@Query("page") int page);

    @GET("genre/movie/list?api_key=ba8e8a114ce7fc27aa71ebec8c0b1afe&language=ru-RU")
    Call<ExampleJanr> EJanr();

    @GET("genre/{id}/movies?api_key=&language=ru-RU")
    Call<ExampleJyrnal> groupList(@Path("id") int grId, @Query("page") int page, @Query("api_key") int api_key, @Query("language") int language);

}
