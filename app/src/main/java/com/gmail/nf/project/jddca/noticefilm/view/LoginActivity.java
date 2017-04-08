package com.gmail.nf.project.jddca.noticefilm.view;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.widget.Toast;

import com.firebase.ui.auth.ErrorCodes;
import com.gmail.nf.project.jddca.noticefilm.R;
import com.gmail.nf.project.jddca.noticefilm.app.App;
import com.gmail.nf.project.jddca.noticefilm.model.firebase.auth.AuthHelper;
import com.gmail.nf.project.jddca.noticefilm.model.injection.component.ActivityComponent;
import com.gmail.nf.project.jddca.noticefilm.presenter.LoginPresenter;
import com.google.android.gms.common.SignInButton;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.signIn) SignInButton signInButton;

    @Inject LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        App.getInstanse().getActivityComponent().inject(this);
        signInButton.setOnClickListener(v -> loginPresenter.login());
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AuthHelper.RC_SIGN_IN) {
            if (resultCode != RESULT_OK){
                Integer error = loginPresenter.getError(data);
                if (error!=null){
                    switch (error){
                        // TODO: 08.04.2017 Окно с ошибкой
                        case ErrorCodes.NO_NETWORK:
                            //NO_NETWORK
                            Toast.makeText(this,"NO_NETWORK",Toast.LENGTH_SHORT).show();
                            break;
                        case ErrorCodes.UNKNOWN_ERROR:
                            //UNKNOWN_ERROR
                            Toast.makeText(this,"UNKNOWN_ERROR",Toast.LENGTH_SHORT).show();
                            break;}
                }else {
                    // User pressed back button
                    Toast.makeText(this,"BACK",Toast.LENGTH_SHORT).show();
                }
            }else {
                loginPresenter.entered();
            }
        }
    }

    @Inject
    void setActivity() {
        loginPresenter.setActivity(this);
    }


    public static Intent createIntent(Activity activity) {
        return new Intent(activity,LoginActivity.class);
    }
}
