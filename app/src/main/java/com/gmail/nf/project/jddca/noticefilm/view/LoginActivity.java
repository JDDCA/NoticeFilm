package com.gmail.nf.project.jddca.noticefilm.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Spinner;

import com.gmail.nf.project.jddca.noticefilm.R;
import com.gmail.nf.project.jddca.noticefilm.presenter.Data.ResultInt;
import com.gmail.nf.project.jddca.noticefilm.presenter.ResponseFilm;

public class LoginActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Spinner spinner;
    private ResponseFilm responseFilm = new ResponseFilm();
    private ResultInt resultInt = new ResultInt();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        spinner = (Spinner) findViewById(R.id.spinner);
//        Ошибка при прокрутке спиныра в низ  ии вообще не стабильный он у меня
        responseFilm.getGenre(spinner, this);
        //initViews();


    }

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        responseFilm.NewFilms(recyclerView);
    }

}
