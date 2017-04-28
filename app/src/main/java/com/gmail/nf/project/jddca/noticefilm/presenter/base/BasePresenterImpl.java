package com.gmail.nf.project.jddca.noticefilm.presenter.base;


import android.os.Parcelable;
import android.support.annotation.Nullable;

import io.reactivex.exceptions.OnErrorNotImplementedException;

/** Базовый класс реадизация
 * @see BasePresenter */
public class BasePresenterImpl implements BasePresenter{
    @Override
    public void onCreate() {
        /*NOP*/
        throw new UnsupportedOperationException("onCreate was not implemented");
    }

    @Override
    public void onStop() {
        /*NOP*/
        throw new UnsupportedOperationException("onStop was not implemented");

    }
}
