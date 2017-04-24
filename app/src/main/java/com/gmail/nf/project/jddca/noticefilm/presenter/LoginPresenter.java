package com.gmail.nf.project.jddca.noticefilm.presenter;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.gmail.nf.project.jddca.noticefilm.model.utils.FirebaseService;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.LoginFragment;

import lombok.NonNull;
import lombok.experimental.PackagePrivate;

/**
 * Created by Yaroslav Lutsenko on 12.04.2017.
 * Презентер для работы с LF
 *
 * @see LoginFragment
 */

@PackagePrivate
public class LoginPresenter implements Presenter {

    private LoginFragment fragment;
    private FirebaseService firebaseService;

    public LoginPresenter() {
        firebaseService = new FirebaseService();
    }

    public void setFragment(LoginFragment fragment) {
        this.fragment = fragment;
    }

    public void login(int PROVIDER) {
        switch (PROVIDER) {
            case FirebaseService.GOOGLE_PROVIDER:
                firebaseService.loginGoogle(fragment);
                break;
            case FirebaseService.ANONYMOUSLY_PROVIDER:
                firebaseService.loginAnonymously(fragment);
                break;
        }
    }


    public void checkResultSigned(int resultCode, @NonNull Intent data) {
        firebaseService.checkResultSigned(fragment, resultCode, data);
    }

    public void logout(@NonNull FragmentActivity activity, int PROVIDER) {
        switch (PROVIDER) {
            case FirebaseService.GOOGLE_PROVIDER:
                firebaseService.logoutGoogle(activity);
                break;
            case FirebaseService.ANONYMOUSLY_PROVIDER:
                firebaseService.logoutAnonymously(activity);
                break;
        }
    }

}
