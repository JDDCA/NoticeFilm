package com.gmail.nf.project.jddca.noticefilm.presenter.login;


import android.content.Intent;
import android.support.annotation.NonNull;

import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.ResultCodes;
import com.gmail.nf.project.jddca.noticefilm.model.utils.FirebaseAuthService;
import com.gmail.nf.project.jddca.noticefilm.presenter.base.BasePresenterImpl;
import com.gmail.nf.project.jddca.noticefilm.view.activity.MainActivity;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.login.LoginFragment;


import static com.gmail.nf.project.jddca.noticefilm.model.utils.FirebaseAuthService.RC_SIGN_IN;

/** Класс реализация входа пользователя
 * @see com.gmail.nf.project.jddca.noticefilm.presenter.login.LoginPresenter */
public class LoginPresenterImpl extends BasePresenterImpl implements LoginPresenter {


    private LoginFragment contract;
    private FirebaseAuthService firebaseAuthService;

    private String token;


    public LoginPresenterImpl(@NonNull LoginFragment contract) {
        this.contract = contract;
        firebaseAuthService = new FirebaseAuthService();
    }

    @Override
    public void loginGoogle() {
        if (!FirebaseAuthService.checkSession()){
            // not signed in
            contract.startActvtFor(firebaseAuthService.getGoogleIntent(),RC_SIGN_IN);
        }else {
            // already signed in
            startMainActivity();
        }
    }

    @Override
    public void loginAnonymously() {
        if (!FirebaseAuthService.checkSession()){
            // not signed in
            firebaseAuthService.getAuthResultTask().addOnCompleteListener(contract.getActvt(), task -> {
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
