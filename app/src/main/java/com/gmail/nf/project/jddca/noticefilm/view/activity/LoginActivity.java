package com.gmail.nf.project.jddca.noticefilm.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.firebase.ui.auth.ResultCodes;
import com.gmail.nf.project.jddca.noticefilm.R;
import com.gmail.nf.project.jddca.noticefilm.presenter.login.LPI;
import com.gmail.nf.project.jddca.noticefilm.presenter.login.LoginPresenter;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.login.LoginFragment;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.login.LoginFragmentImpl;
import com.google.android.gms.common.SignInButton;

import butterknife.BindView;
import butterknife.ButterKnife;

/**Activity для авторизации пользователя.*/
public class LoginActivity extends AppCompatActivity implements Login{

    @BindView(R.id.signInGoogleActivity)
    SignInButton signInButton;
    @BindView(R.id.anonymousSignInActivity)
    TextView signInAnonymously;

    private LoginPresenter presenter;


    public LoginActivity() {
        presenter = new LPI(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);
        signInButton.setOnClickListener(v -> presenter.loginGoogle());
        signInAnonymously.setOnClickListener(v -> presenter.loginAnonymously());
        presenter.onCreate();
    }

    public static Intent createIntent (@NonNull Activity activity){
        return new Intent(activity,LoginActivity.class);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LoginPresenter.RC_SIGN_IN)
            presenter.checkResultSigned(resultCode,data);
    }

    @Override
    public Activity getAct() {
        return this;
    }

    @Override
    public void toLog(String s) {
        Log.i("TEMP", "toLog: "+s);
    }

    @Override
    public void showError(Throwable throwable) {
        Log.e("TEMP", "showError: ", throwable);
    }
}
