package com.gmail.nf.project.jddca.noticefilm.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.gmail.nf.project.jddca.noticefilm.R;
import com.gmail.nf.project.jddca.noticefilm.model.utils.FirebaseUtils;

import java.util.ArrayList;

import devlight.io.library.ntb.NavigationTabBar;
import lombok.experimental.PackagePrivate;


/**
 * Created by Yaroslav Lutsenko on 12.04.2017.
 * Главное Activity всего приложения
 */
@PackagePrivate
public class MainActivity extends FragmentActivity {


    private PagerAdapter pagerAdapter;
    private ViewPager vp;
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
        setContentView(R.layout.main_activity_with_ntb);
        initNtb();
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

//    @OnClick(R.id.advice_btn)
//    public void onClick(View view) {
//        randomID = (int) (Math.random() + 500);
//    }


    /*Метод для инициализации NTB,
    может конечно можно вынести в отдельный класс,
    но я пока не успел, пока думаю так сойдет
     */
    private void initNtb() {
        // определяем ViewPager
        vp = (ViewPager)findViewById(R.id.vp_horisontal);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        vp.setAdapter(pagerAdapter);

        final String[] colors = getResources().getStringArray(R.array.color_icons);//массив цветов для выбранной иконки
        final NavigationTabBar ntb = (NavigationTabBar) findViewById(R.id.ntb_horisontal);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();

        //Устанавливаем иконки, цвет выбраной иконки и т.д.
        models.add(new NavigationTabBar.Model.Builder(
                getResources().getDrawable(R.drawable.ic_light_bulb_24dp),
                Color.parseColor(colors[0]))//немного поиксперементировал с цветами
                //.badgeTitle("1") устанавливает что-то вроде подсказки над иконкой, думаю пока нет необходимости ставить ее
                .title("Generate")
                .build());

        models.add(new NavigationTabBar.Model.Builder(
                getResources().getDrawable(R.drawable.ic_film_favorite_24dp),
                Color.parseColor(colors[1]))
                .title("Favorite")
                .build());

        models.add(new NavigationTabBar.Model.Builder(
                getResources().getDrawable(R.drawable.ic_play_movie_24dp),
                Color.parseColor(colors[2]))
                .title("Play")
                .build());

        models.add(new NavigationTabBar.Model.Builder(
                getResources().getDrawable(R.drawable.ic_profile_24dp),
                Color.parseColor(colors[3]))
                .title("Profile")
                .build());

        ntb.setModels(models);//подключает модели к NTB
        ntb.setViewPager(vp, 0);//Подключает ViewPager и устанавливает начальную позицию


        /*
        Листнеры, в них пока особо не успел разбираться, без них пока все вроде работает

        ntb.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ntb.getModels().get(position).hideBadge();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        */
    }


}
