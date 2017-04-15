package com.gmail.nf.project.jddca.noticefilm.view.activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.gmail.nf.project.jddca.noticefilm.R;
import com.gmail.nf.project.jddca.noticefilm.model.utils.FirebaseUtils;
import com.gmail.nf.project.jddca.noticefilm.presenter.MainPresenter;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.MovieFragment;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.NavigationTabFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lombok.Getter;
import lombok.experimental.PackagePrivate;


/**
 * Created by Yaroslav Lutsenko on 12.04.2017.
 * Главное Activity всего приложения
 */
@PackagePrivate
public class MainActivity extends FragmentActivity {

    public final String TAG = getClass().getSimpleName();

//    @Getter
//    private int randomID;
//
//
//    MainPresenter mainPresenter;
//
//    @BindView(R.id.advice_btn)
//    Button btnAdvice;
//
//    @BindView(R.id.genre_spinner)
//    Spinner genresSpinner;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_fragment);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = new NavigationTabFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }






//        mainPresenter = new MainPresenter();
//        mainPresenter.setActivity(this);
//        ButterKnife.bind(this);


        // Фрагмент с фильмом | Fragment with movie info
//        MovieFragment movieFragment = new MovieFragment();
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//
//        transaction.replace(R.id.fragment_container, movieFragment);
//        transaction.addToBackStack(null);
//        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//        transaction.commit();
//
//        // Создание spinner'а и присоединение адаптера с жанрами | Creating Spinner with genres adapter
//        ArrayAdapter<CharSequence> adapterGenres = ArrayAdapter.createFromResource(this, R.array.genres, android.R.layout.simple_spinner_item);
//        genresSpinner.setAdapter(adapterGenres);



    }

    @Override
    protected void onResume() {
        super.onResume();
        // для дополнительной проверки ?
        checkSession();
    }

    public static Intent createIntent(@NonNull Activity activity) {
        return new Intent(activity, MainActivity.class);
    }

    private void checkSession() {
        if (!FirebaseUtils.checkSession()) {
            startActivity(LoginActivity.createIntent(this));
            finish();
        }
    }

//    @OnClick(R.id.advice_btn)
//    public void onClick(View view) {
//        randomID = (int) (Math.random() + 500);
//    }


}
