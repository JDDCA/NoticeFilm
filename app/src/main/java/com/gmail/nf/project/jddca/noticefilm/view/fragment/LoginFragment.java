package com.gmail.nf.project.jddca.noticefilm.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.auth.ErrorCodes;
import com.gmail.nf.project.jddca.noticefilm.R;
import com.gmail.nf.project.jddca.noticefilm.model.utils.DialogFactory;
import com.gmail.nf.project.jddca.noticefilm.model.utils.FirebaseService;
import com.gmail.nf.project.jddca.noticefilm.presenter.login.LoginPresenter;
import com.gmail.nf.project.jddca.noticefilm.presenter.login.LoginPresenterImpl;
import com.gmail.nf.project.jddca.noticefilm.view.contract.LoginFragmentContract;
import com.google.android.gms.common.SignInButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Yaroslav Lutsenko on 12.04.2017.
 * Класс для создания login fragment
 */

public class LoginFragment extends BaseFragment implements LoginFragmentContract {

    private LoginPresenter presenter;

    @BindView(R.id.signInGoogle)
    SignInButton signInButton;
    @BindView(R.id.anonymousSignIn)
    TextView signInAnonymously;

    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //// TODO: 24.04.2017 поврот экрана???
        presenter = new LoginPresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        signInButton.setOnClickListener(v -> presenter.loginGoogle());
        signInAnonymously.setOnClickListener(v -> presenter.loginAnonymously());
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        // стирает ссылку на презентер и освобождает память
        presenter = null;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == FirebaseService.RC_SIGN_IN) {
            presenter.checkResultSigned(resultCode, data);
        }
    }

    @Override
    public Fragment getFrgmnt() {
        return this;
    }

    @Override
    public void showError(Integer response) {
        if (response != null) {
            if (response == ErrorCodes.NO_NETWORK)
            DialogFactory.newInstance(R.string.error, R.string.dialog_network_error)
                    .show(getFragmentManager(), DialogFactory.DIALOG_ERROR);
            if (response == ErrorCodes.UNKNOWN_ERROR)
                DialogFactory.newInstance(R.string.error, R.string.dialog_network_error)
                        .show(getFragmentManager(), DialogFactory.DIALOG_ERROR);
        }else{
            DialogFactory.newInstance(R.string.error, R.string.dialog_cancel_error)
                    .show(getFragmentManager(), DialogFactory.DIALOG_ERROR);
        }
    }

}
