package com.gmail.nf.project.jddca.noticefilm.view;

import android.support.v4.app.Fragment;

import com.gmail.nf.project.jddca.noticefilm.presenter.SingleFragmentActivity;

public class LoginActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return new LoginFragment();
    }
}
