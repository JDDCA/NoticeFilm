package com.gmail.nf.project.jddca.noticefilm.presenter.login;


import android.content.Intent;
import android.support.annotation.NonNull;

import com.gmail.nf.project.jddca.noticefilm.presenter.base.BasePresenter;

public interface LoginPresenter extends BasePresenter {

    void loginGoogle ();
    void loginAnonymously ();
    void checkResultSigned (int resultCode, @NonNull Intent data);

}
