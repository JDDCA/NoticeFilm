package com.gmail.nf.project.jddca.noticefilm.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.widget.Button;

import com.firebase.ui.auth.IdpResponse;
import com.gmail.nf.project.jddca.noticefilm.R;
import com.gmail.nf.project.jddca.noticefilm.app.App;
import com.gmail.nf.project.jddca.noticefilm.presenter.LoginPresenter;
import com.gmail.nf.project.jddca.noticefilm.presenter.MainPresenter;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.NonNull;
import lombok.experimental.PackagePrivate;
import rx.Observable;

@PackagePrivate
public class MainActivity extends FragmentActivity{

    final String TAG = getClass().getSimpleName();

    @BindView(R.id.logOutGoogle)
    AppCompatButton logOutGoogle;
    @BindView(R.id.logOutAnonymously)
    AppCompatButton logOutAnonymously;
    @BindView(R.id.showProvider)
    AppCompatButton showProvider;
    @BindView(R.id.convertToGoogle)
    AppCompatButton convertToGoogle;

    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.getInstanse().getActivityComponent().inject(this);
        ButterKnife.bind(this);
        presenter.setActivity(this);
        presenter.checkSignIn();
        logOutGoogle.setOnClickListener(v -> presenter.logOutGoogle());
        logOutAnonymously.setOnClickListener(v -> presenter.logOutAnonymously());
        showProvider.setOnClickListener(v -> Log.i(TAG, "onCreate: "+presenter.isAnonymous()));
        convertToGoogle.setOnClickListener(v -> presenter.convertToGoogle());
    }

    public static Intent createIntent (@NonNull Activity activity,@NonNull String token){
        return new Intent(activity,MainActivity.class).putExtra("token",token);
    }

}
