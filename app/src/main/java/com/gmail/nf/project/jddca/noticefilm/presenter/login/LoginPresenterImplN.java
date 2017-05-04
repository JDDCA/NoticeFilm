package com.gmail.nf.project.jddca.noticefilm.presenter.login;


import android.content.Intent;
import android.support.annotation.NonNull;

import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.ResultCodes;
import com.gmail.nf.project.jddca.noticefilm.model.auth.AuthorizationService;
import com.gmail.nf.project.jddca.noticefilm.model.auth.AuthorizationServiceImpl;
import com.gmail.nf.project.jddca.noticefilm.presenter.base.BasePresenterImpl;
import com.gmail.nf.project.jddca.noticefilm.view.activity.MainActivity;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.login.LoginFragment;

public class LoginPresenterImplN extends BasePresenterImpl implements LoginPresenter {

    private LoginFragment fragment;
    private AuthorizationService authService;

    public LoginPresenterImplN(LoginFragment fragment) {
        this.fragment = fragment;
        authService = new AuthorizationServiceImpl();
    }

    @Override
    public void loginGoogle() {
        if (authService.getUser() != null)
            startMainActiviity();
        else
            fragment.startActvtFor(authService.getSingleGoogleIntent(), AuthorizationService.AUTH_RESULT);
    }

    @Override
    public void loginAnonymously() {

    }

    @Override
    public void checkResultSigned(int resultCode, @NonNull Intent data) {
        if (resultCode == ResultCodes.OK)
            startMainActiviity();
        else {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (response != null)
                fragment.showError(response.getErrorCode());
            else
                fragment.showError(null);
        }
    }

    private void startMainActiviity() {
        fragment.startActvt(MainActivity.createIntent(fragment.getActvt()));
        fragment.getActvt().finish();
    }
}
