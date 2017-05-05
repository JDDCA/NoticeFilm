package com.gmail.nf.project.jddca.noticefilm.presenter.login;


import android.content.Intent;
import android.support.annotation.NonNull;

import com.gmail.nf.project.jddca.noticefilm.presenter.base.BasePresenter;

/**Интерефес для работы с авторизацией пользователя*/
public interface LoginPresenter extends BasePresenter {

    /** Метод для входа через Google+ */
    void loginGoogle ();
    /**Метод аннонимного входа*/
    void loginAnonymously ();
    /*** Метод проверяет результат входа пользователя и сохраняет токен при успешном входе.*/
    void checkResultSigned (int resultCode, @NonNull Intent data);

}
