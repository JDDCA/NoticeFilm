package com.gmail.nf.project.jddca.noticefilm.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import com.gmail.nf.project.jddca.noticefilm.R;
import com.gmail.nf.project.jddca.noticefilm.presenter.Data.ResultInt;
import com.gmail.nf.project.jddca.noticefilm.presenter.ResponseFilm;

public class LoginActivity extends AppCompatActivity {


    private Spinner spinner;
    private ResponseFilm responseFilm = new ResponseFilm();
    private ResultInt resultInt = new ResultInt();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        spinner = (Spinner)findViewById(R.id.spinner);
        responseFilm.getGenre(spinner,this);

    }
}
