package com.gmail.nf.project.jddca.noticefilm.presenter.login;


import android.content.Intent;
import android.support.annotation.NonNull;

import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.ResultCodes;
import com.gmail.nf.project.jddca.noticefilm.model.utils.FirebaseService;
import com.gmail.nf.project.jddca.noticefilm.presenter.base.BasePresenterImpl;
import com.gmail.nf.project.jddca.noticefilm.view.activity.MainActivity;
import com.gmail.nf.project.jddca.noticefilm.view.contract.LoginFragmentContract;


import static com.gmail.nf.project.jddca.noticefilm.model.utils.FirebaseService.RC_SIGN_IN;

/** Класс реализация входа пользователя
 * @see com.gmail.nf.project.jddca.noticefilm.presenter.login.LoginPresenter */
public class LoginPresenterImpl extends BasePresenterImpl implements LoginPresenter {


    private LoginFragmentContract contract;
    private FirebaseService firebaseService;

    private String token;


    public LoginPresenterImpl(@NonNull LoginFragmentContract contract) {
        this.contract = contract;
        firebaseService = new FirebaseService();
    }

    @Override
    public void loginGoogle() {
        if (!FirebaseService.checkSession()){
            // not signed in
            contract.startActvtFor(firebaseService.getGoogleIntent(),RC_SIGN_IN);
        }else {
            // already signed in
            startMainActivity();
        }
    }

    @Override
    public void loginAnonymously() {
        if (!FirebaseService.checkSession()){
            // not signed in
            firebaseService.getAuthResultTask().addOnCompleteListener(contract.getActvt(), task -> {
                if (task.isSuccessful()){
                    startMainActivity();
                }else {
                    contract.showError(ErrorCodes.NO_NETWORK);
                }
            });
        }else {
            // already signed in
            startMainActivity();
        }
    }

    @Override
    public void checkResultSigned(int resultCode, @NonNull Intent data) {
        IdpResponse response = IdpResponse.fromResultIntent(data);
        if (resultCode != ResultCodes.OK) {
            // Sign in failed
            if (response == null) {
                contract.showError(null);
            }else {
                contract.showError(response.getErrorCode());
            }
        } else {
            setToken(response);
            startMainActivity();
        }
    }

    private void setToken(IdpResponse response) {
        token = response.getIdpToken();
    }

    private void startMainActivity (){
        contract.startActvt(MainActivity.createIntent(contract.getActvt()));
        contract.finishActvt();
    }
}
