package com.gmail.nf.project.jddca.noticefilm.view.fragment.context;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.gmail.nf.project.jddca.noticefilm.view.fragment.context.ContextFragment;

/**Базовый класс реализации базового интерфейса для фрагментов
 * @see ContextFragment */

public abstract class ContextFragmentImpl extends Fragment implements ContextFragment {

    @Override
    public Context getCntxt() {
        return getFrgmnt().getContext();
    }

    @Override
    public Activity getActvt() {
        return getFrgmnt().getActivity();
    }

    @Override
    public void startActvtFor(@NonNull Intent intent, int requestCode) {
        getFrgmnt().startActivityForResult(intent, requestCode);
    }

    @Override
    public void startActvt(@NonNull Intent intent) {
        getFrgmnt().startActivity(intent);
    }

    @Override
    public void finishActvt() {
        getActvt().finish();
    }

}
