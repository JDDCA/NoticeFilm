package com.gmail.nf.project.jddca.noticefilm.presenter.login;


import android.content.Intent;
import android.support.annotation.NonNull;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.ResultCodes;
import com.gmail.nf.project.jddca.noticefilm.presenter.base.BasePresenterImpl;
import com.gmail.nf.project.jddca.noticefilm.view.activity.Login;
import com.gmail.nf.project.jddca.noticefilm.view.activity.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Collections;

public class LPI extends BasePresenterImpl implements LoginPresenter{

    private Login login;


    public LPI(Login login) {
        this.login = login;
    }

    @Override
    public void onCreate() {
        login.toLog("CREATED");
        if (FirebaseAuth.getInstance().getCurrentUser()!=null)
            updateUI(true);
    }

    @Override
    public void onStop() {

    }

    @Override
    public void loginGoogle() {
        login.getAct().startActivityForResult(getProviderGoogleIntent(),LoginPresenter.RC_SIGN_IN);
    }

    @Override
    public void loginAnonymously() {

    }

    @Override
    public void checkResultSigned(int resultCode, @NonNull Intent data) {
        IdpResponse response = IdpResponse.fromResultIntent(data);
        if (resultCode == ResultCodes.OK)
            updateUI(true);
    }

    private void updateUI (boolean b) {
        if (b){
            login.getAct().startActivity(MainActivity.createIntent(login.getAct()));
            login.getAct().finish();
            login.toLog("ENTERED!");
        }
    }

    private Intent getProviderGoogleIntent () {
        return AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setIsSmartLockEnabled(false)
                .setProviders(Collections.singletonList(new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                .build();
    }
}
