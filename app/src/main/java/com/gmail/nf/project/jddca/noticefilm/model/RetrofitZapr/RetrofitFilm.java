package com.gmail.nf.project.jddca.noticefilm.model.RetrofitZapr;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bigi on 11.04.2017.
 */

public class RetrofitFilm {
    private static final String URL = "https://api.themoviedb.org/3/";

    public InterfFilm RetrZap() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfFilm service = retrofit.create(InterfFilm.class);
        return service;
        //tst
    }

}
