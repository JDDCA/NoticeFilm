package com.gmail.nf.project.jddca.noticefilm.presenter;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.gmail.nf.project.jddca.noticefilm.model.DataManager;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.LoginFragment;

import lombok.NonNull;
import lombok.experimental.PackagePrivate;

/**
 * Created by Yaroslav Lutsenko on 12.04.2017.
 * Презентер для работы с LF
 * @see LoginFragment
 */

@PackagePrivate
public class LoginPresenter {

    private LoginFragment fragment;
    private DataManager dataManager;

    public LoginPresenter() {
        dataManager = DataManager.getInstance();
    }

    /**Метод для делегирования входа
     * Sign In method
     * @see DataManager#login(Fragment, int) */
    public void login (int PROVIDER){
        dataManager.login(fragment,PROVIDER);
    }

    public void checkResult (int resultCode, @NonNull Intent data){
        dataManager.checkResultSigned(fragment,resultCode,data);
    }



    public void setFragment(LoginFragment fragment) {
        this.fragment = fragment;
    }
}
