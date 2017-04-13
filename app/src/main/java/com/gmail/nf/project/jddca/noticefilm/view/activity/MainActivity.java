package com.gmail.nf.project.jddca.noticefilm.view.activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.gmail.nf.project.jddca.noticefilm.R;
import com.gmail.nf.project.jddca.noticefilm.model.utils.FirebaseUtils;
import com.gmail.nf.project.jddca.noticefilm.presenter.MainPresenter;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.MovieFragment;

import javax.inject.Inject;

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

    @Getter
    private int randomID;

    @Inject
    MainPresenter mainPresenter;

    @BindView(R.id.advice_btn)
    Button btnAdvice;

    @BindView(R.id.genre_spinner)
    Spinner genresSpinner;

    /*@BindView(R.id.logOutGoogle)
    AppCompatButton logOutGoogle;
    @BindView(R.id.logOutAnonymously)
    AppCompatButton logOutAnonymously;
    @BindView(R.id.showProvider)
    AppCompatButton showProvider;
    @BindView(R.id.convertToGoogle)
    AppCompatButton convertToGoogle;*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenter();
        mainPresenter.setActivity(this);
        ButterKnife.bind(this);


        // Фрагмент с фильмом | Fragment with movie info
        MovieFragment movieFragment = new MovieFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, movieFragment);
        transaction.addToBackStack(null);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();

        // Создание spinner'а и присоединение адаптера с жанрами | Creating Spinner with genres adapter
        ArrayAdapter<CharSequence> adapterGenres = ArrayAdapter.createFromResource(this, R.array.genres, android.R.layout.simple_spinner_item);
        genresSpinner.setAdapter(adapterGenres);

//        showProvider.setOnClickListener(v -> Log.i(TAG, "onCreate: "+FirebaseUtils.isAnonymousUser()));
//        logOutGoogle.setOnClickListener(v -> mainPresenter.logout(FirebaseUtils.GOOGLE_PROVIDER));
//        logOutAnonymously.setOnClickListener(v -> mainPresenter.logout(FirebaseUtils.ANONYMOUSLY_PROVIDER) );
//        convertToGoogle.setOnClickListener(v -> {});


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

    @OnClick(R.id.advice_btn)
    public void onClick(View view) {
        randomID = (int) (Math.random() + 500);
    }


}
