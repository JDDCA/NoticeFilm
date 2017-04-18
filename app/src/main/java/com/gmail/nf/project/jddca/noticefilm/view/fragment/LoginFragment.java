package com.gmail.nf.project.jddca.noticefilm.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.nf.project.jddca.noticefilm.R;
import com.gmail.nf.project.jddca.noticefilm.model.utils.FirebaseUtils;
import com.gmail.nf.project.jddca.noticefilm.presenter.LoginPresenter;
import com.google.android.gms.common.SignInButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Yaroslav Lutsenko on 12.04.2017.
 * Класс для создания login fragment
 */

public class LoginFragment extends Fragment {

    private LoginPresenter loginPresenter;

    @BindView(R.id.signInGoogle)
    SignInButton signInButton;
    @BindView(R.id.anonymousSignIn)
    TextView signInAnonymously;

    private Unbinder unbinder;

    public LoginFragment() {
        loginPresenter = new LoginPresenter();
        loginPresenter.setFragment(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        signInButton.setOnClickListener(v -> loginPresenter.login(FirebaseUtils.GOOGLE_PROVIDER));
        signInAnonymously.setOnClickListener(v -> loginPresenter.login(FirebaseUtils.ANONYMOUSLY_PROVIDER));
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        // стирает ссылку на презентер и освобождает память
        loginPresenter = null;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == FirebaseUtils.RC_SIGN_IN) {
            loginPresenter.checkResult(resultCode, data);
        }
    }
}
