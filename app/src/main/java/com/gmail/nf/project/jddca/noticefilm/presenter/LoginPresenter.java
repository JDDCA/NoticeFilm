package com.gmail.nf.project.jddca.noticefilm.presenter;

import android.content.Intent;

import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.ResultCodes;
import com.gmail.nf.project.jddca.noticefilm.model.firebase.FirebaseManager;
import com.gmail.nf.project.jddca.noticefilm.view.LoginActivity;
import com.gmail.nf.project.jddca.noticefilm.view.MainActivity;

import javax.inject.Inject;

import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.PackagePrivate;


/**
 * Created by Yaroslav Lutsenko on 07.04.2017.
 * It's Presenter for LoginActivity
 *
 * @see com.gmail.nf.project.jddca.noticefilm.view.LoginActivity
 */

@PackagePrivate
public class LoginPresenter {

    @Setter
    LoginActivity activity;

    FirebaseManager firebaseManager;

    @Inject public LoginPresenter(FirebaseManager firebaseManager) {
        this.firebaseManager = firebaseManager;
    }

    public void login() {
        firebaseManager.login(activity);
    }

    public Integer getError(@NonNull Intent data) {
        return firebaseManager.getResultError(data);
    }

    public void entered() {
        activity.startActivity(MainActivity.createIntent(activity));
        activity.finish();
    }
}
