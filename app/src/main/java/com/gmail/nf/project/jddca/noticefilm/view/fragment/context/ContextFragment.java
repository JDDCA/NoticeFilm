package com.gmail.nf.project.jddca.noticefilm.view.fragment.context;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

/**Базовый интерфейс для работы с фрагментами*/
public interface ContextFragment {
    Context getCntxt ();
    Fragment getFrgmnt ();
    Activity getActvt ();
    void startActvtFor (@NonNull Intent intent, int requestCode);
    void startActvt (@NonNull Intent intent);
    void finishActvt ();

}
