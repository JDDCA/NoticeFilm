package com.gmail.nf.project.jddca.noticefilm.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.gmail.nf.project.jddca.noticefilm.model.utils.FirebaseAuthService;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.generate.GenerateFragmentImpl;

import lombok.experimental.PackagePrivate;


/**
 * Created by Yaroslav Lutsenko on 12.04.2017.
 * Главное Activity всего приложения
 */

@PackagePrivate
public class MainActivity extends SingleFragmentActivity{

    public final String TAG = getClass().getSimpleName();

    /**Метод устанавливает начальный фрагмент {@link GenerateFragmentImpl}*/
    @Override
    protected Fragment createFragment() {
        return new GenerateFragmentImpl();
    }

    @Override
    protected String getFragmentTAG() {
        return GenerateFragmentImpl.GENERATE_TAG;
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

    /**Метод для проверки авторизации пользователя.*/
    private void checkSession() {
        if (!FirebaseAuthService.checkSession()) {
            startActivity(LoginActivity.createIntent(this));
            finish();
        }
    }

    public static Intent createIntent(@NonNull Activity activity) {
        return new Intent(activity, MainActivity.class);
    }


}
