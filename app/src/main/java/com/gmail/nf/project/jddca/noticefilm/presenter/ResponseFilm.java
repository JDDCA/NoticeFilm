package com.gmail.nf.project.jddca.noticefilm.presenter;

import android.animation.IntArrayEvaluator;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.gmail.nf.project.jddca.noticefilm.model.POJOS.REST.Genre_of_films.ExampleJanr;
import com.gmail.nf.project.jddca.noticefilm.model.POJOS.REST.Genre_of_films.GenreJanr;
import com.gmail.nf.project.jddca.noticefilm.model.RetrofitZapr.InterfFilm;
import com.gmail.nf.project.jddca.noticefilm.model.RetrofitZapr.RetrofitFilm;
import com.gmail.nf.project.jddca.noticefilm.presenter.Data.ResultInt;
import com.gmail.nf.project.jddca.noticefilm.view.LoginActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bigi on 11.04.2017.
 */

public class ResponseFilm {
    private static final String IMEG = "https://image.tmdb.org/t/p/w500";
    private static final String KEY = "ba8e8a114ce7fc27aa71ebec8c0b1afe";
    private static final String language = "ru-RU";
    private List<GenreJanr> books ;
    private int ID,IDj=0,idJn;

    public void getGenre(Spinner spinner, Context context) {
        //Ketika Aplikasi mengambil data kita akan melihat progress dialog
        final ProgressDialog loading = ProgressDialog.show(context,"Fetching Data","Please Wait..",false,false);

        RetrofitFilm retrofitFilm = new RetrofitFilm();
        InterfFilm API = retrofitFilm.RetrZap();
        Call<ExampleJanr> call = API.EJanr(KEY,language);
        call.enqueue(new Callback<ExampleJanr>() {
            @Override
            public void onResponse(Call<ExampleJanr> call, Response<ExampleJanr> response) {
                loading.dismiss();
                List<GenreJanr> buku = response.body().getListbuku();
                books = buku;
                showList(spinner,context);

            }

            @Override
            public void onFailure(Call<ExampleJanr> call, Throwable t) {

            }
        });
    }

    private ResultInt showList(Spinner spinner, Context context) {
        //String array untuk menyimpan nama semua nama buku
        String[] items = new String[books.size()+1];
        items[0]= "OOOL random";
        for (int i = 1; i < books.size(); i++) {

            items[i] = books.get(i).getName();
        }

        //Membuat Array Adapter for spinner
        ArrayAdapter adapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_dropdown_item, items);

        ResultInt r = new ResultInt();
        //setting adapter untuk spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                r.setIdNameJ(i);
                r.setIdGanra(books.get(i).getId());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return r;
    }


}
