package com.gmail.nf.project.jddca.noticefilm.view.fragment.login;


import com.gmail.nf.project.jddca.noticefilm.view.fragment.context.ContextFragment;

/** Интерфейс для отображения ошибок при авторизации пользователя*/
public interface LoginFragment extends ContextFragment {

    void showError(Integer integer);

}
