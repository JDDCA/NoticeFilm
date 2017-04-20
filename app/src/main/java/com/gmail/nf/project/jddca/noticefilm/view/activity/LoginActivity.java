package com.gmail.nf.project.jddca.noticefilm.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.gmail.nf.project.jddca.noticefilm.view.base.SingleFragmentActivity;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.GenerateFragment;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.LoginFragment;

/**Activity для авторизации пользователя.*/
public class LoginActivity extends SingleFragmentActivity{

    /**Метод устанавливает фрагмент для LoginActivity {@link LoginFragment}*/
    @Override
    protected Fragment createFragment() {
        return new LoginFragment();
    }

    /**@see SingleFragmentActivity#createButtonNavigationFragment()*/
    @Override
    protected boolean createButtonNavigationFragment() {
        return false;
    }

    public static Intent createIntent (@NonNull Activity activity){
        return new Intent(activity,LoginActivity.class);
    }
}
