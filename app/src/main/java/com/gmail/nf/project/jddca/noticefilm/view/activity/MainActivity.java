package com.gmail.nf.project.jddca.noticefilm.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;

import com.gmail.nf.project.jddca.noticefilm.R;
import com.gmail.nf.project.jddca.noticefilm.model.utils.FirebaseUtils;
import com.gmail.nf.project.jddca.noticefilm.presenter.MainPresentr;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.experimental.PackagePrivate;


/**
 * Created by Yaroslav Lutsenko on 12.04.2017.
 * Главное Activity всего приложения
 */
@PackagePrivate
public class MainActivity extends FragmentActivity {

    public final String TAG = getClass().getSimpleName();

    MainPresentr mainPresentr;
    @BindView(R.id.logOutGoogle)
    AppCompatButton logOutGoogle;
    @BindView(R.id.logOutAnonymously)
    AppCompatButton logOutAnonymously;
    @BindView(R.id.showProvider)
    AppCompatButton showProvider;
    @BindView(R.id.convertToGoogle)
    AppCompatButton convertToGoogle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresentr = new MainPresentr();
        mainPresentr.setActivity(this);
        ButterKnife.bind(this);
        showProvider.setOnClickListener(v -> Log.i(TAG, "onCreate: "+FirebaseUtils.isAnonymousUser()));
        logOutGoogle.setOnClickListener(v -> mainPresentr.logout(FirebaseUtils.GOOGLE_PROVIDER));
        logOutAnonymously.setOnClickListener(v -> mainPresentr.logout(FirebaseUtils.ANONYMOUSLY_PROVIDER) );
        convertToGoogle.setOnClickListener(v -> {});
    }

    @Override
    protected void onResume() {
        super.onResume();
        // для дополнительной проверки ?
        checkSession();
    }

    public static Intent createIntent (@NonNull Activity activity){
        return new Intent(activity, MainActivity.class);
    }

    private void checkSession (){
        if (!FirebaseUtils.checkSession()){
            startActivity(LoginActivity.createIntent(this));
            finish();
        }

    }
}
