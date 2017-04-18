package com.gmail.nf.project.jddca.noticefilm.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;

import com.gmail.nf.project.jddca.noticefilm.R;
import com.gmail.nf.project.jddca.noticefilm.model.utils.FirebaseUtils;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.FragmentMove;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.FragmentTest;

import lombok.experimental.PackagePrivate;

import static com.gmail.nf.project.jddca.noticefilm.R.id.action_favorite;
import static com.gmail.nf.project.jddca.noticefilm.R.id.action_generate;
import static com.gmail.nf.project.jddca.noticefilm.R.id.action_list;
import static com.gmail.nf.project.jddca.noticefilm.R.id.action_profile;
import static com.gmail.nf.project.jddca.noticefilm.R.id.action_watch;


/**
 * Created by Yaroslav Lutsenko on 12.04.2017.
 * Главное Activity всего приложения
 */
@PackagePrivate
public class MainActivity extends FragmentActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public final String TAG = getClass().getSimpleName();
    BottomNavigationView bnv;
    Fragment mFragment;
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
        setContentView(R.layout.activity_main_container);

        bnv = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bnv.setOnNavigationItemSelectedListener(this);
        bnv.setSelectedItemId(R.id.action_generate);//Выбранный элемент при запуске
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case action_generate:
                mFragment = FragmentMove.newInctace(0, "generate");
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.movie_container, mFragment)
                        .commit();
                //changeFragment(new FragmentMove());
                break;
            case action_favorite:
                mFragment = FragmentTest.newInctace(1, "favorite");
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.movie_container, mFragment)
                        .commit();
                //changeFragment(new FragmentTest());
                break;
            case action_list:
                mFragment = FragmentMove.newInctace(2, "list");
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.movie_container, mFragment)
                        .commit();
                break;
            case action_watch:
                mFragment = FragmentTest.newInctace(3, "watch");
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.movie_container, mFragment)
                        .commit();
                break;
            case action_profile:
                mFragment = FragmentMove.newInctace(4, "profile");
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.movie_container, mFragment)
                        .commit();
                break;

        }
        return true;
    }

    /**
     * метод для добавления/замены фрагмента
     * если использовать его тогда нет необходимости в методе NewInstace во фрагментах
     */
    private void changeFragment(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_container, fragment)
                    //.addToBackStack(TAG) нужен?
                    .commit();
        }else {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_container, fragment)
                    .commit();
        }
    }

//    @OnClick(R.id.advice_btn)
//    public void onClick(View view) {
//        randomID = (int) (Math.random() + 500);
//    }


}
