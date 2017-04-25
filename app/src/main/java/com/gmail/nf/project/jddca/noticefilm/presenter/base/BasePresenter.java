package com.gmail.nf.project.jddca.noticefilm.presenter.base;


import android.os.Parcelable;
import android.support.annotation.Nullable;

/**Базовый интерфейс который для жизненного цикла фрагмента*/
public interface BasePresenter {

    void onCreate();
    void onStop();
    void onSave (@Nullable Parcelable value);
}
