package com.gmail.nf.project.jddca.noticefilm.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.firebase.ui.auth.IdpResponse;
import com.gmail.nf.project.jddca.noticefilm.R;
import com.gmail.nf.project.jddca.noticefilm.app.App;
import com.gmail.nf.project.jddca.noticefilm.presenter.LoginPresenter;
import com.gmail.nf.project.jddca.noticefilm.presenter.MainPresenter;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import butterknife.ButterKnife;
import lombok.NonNull;
import lombok.experimental.PackagePrivate;
import rx.Observable;

@PackagePrivate
public class MainActivity extends AppCompatActivity {

    final String TAG = getClass().getSimpleName();

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
    }

    public static Intent createIntent (@NonNull Activity activity){
        return new Intent(activity,MainActivity.class);
    }

}
