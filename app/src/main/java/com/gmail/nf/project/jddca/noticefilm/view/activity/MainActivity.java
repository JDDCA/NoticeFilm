package com.gmail.nf.project.jddca.noticefilm.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.gmail.nf.project.jddca.noticefilm.model.utils.FirebaseService;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.generate.GenerateFragmentImpl;

import lombok.experimental.PackagePrivate;


/**
 * Created by Yaroslav Lutsenko on 12.04.2017.
 * Главное Activity всего приложения
 */

@PackagePrivate
public class MainActivity extends SingleFragmentActivity{

    public final String TAG = getClass().getSimpleName();

//        transaction.replace(R.id.fragment_container, movieFragment);
//        transaction.addToBackStack(null);
//        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//        transaction.commit();


    /**Метод устанавливает начальный фрагмент {@link GenerateFragmentImpl}*/
    @Override
    protected Fragment createFragment() {
        return new GenerateFragmentImpl();
    }

    /**@see SingleFragmentActivity#createButtonNavigationFragment()*/
    @Override
    protected boolean createButtonNavigationFragment() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkSession();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // для дополнительной проверки авторизации пользователя?
//        checkSession();
    }

    /**Метод для проверки авторизации пользователя.*/
    private void checkSession() {
        if (!FirebaseService.checkSession()) {
            startActivity(LoginActivity.createIntent(this));
            finish();
        }
    }


    public static Intent createIntent(@NonNull Activity activity) {
        return new Intent(activity, MainActivity.class);
    }


}
