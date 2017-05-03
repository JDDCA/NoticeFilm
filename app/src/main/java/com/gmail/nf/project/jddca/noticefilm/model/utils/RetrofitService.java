package com.gmail.nf.project.jddca.noticefilm.model.utils;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    public final static int RANDOM_FILM = -1;
    public final static int FIRST = 0;
    public final static int MAX_PAGES = 1000;
    public final static String INCLUDE_ABULT = "false";
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String BASE_PATH_POSTER = "https://image.tmdb.org/t/p/w500";
    public static final String UNKNOW_HOST_EXEPTION = "Unable to resolve host";


    public static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

}
