package com.gmail.nf.project.jddca.noticefilm.view.fragment.list;


import android.view.View;

import com.gmail.nf.project.jddca.noticefilm.view.fragment.context.ContextFragment;

public interface ListFragment extends ContextFragment {

    void showError (Throwable throwable);
    void toLog (String s);
    View getInfoPage();
    View getBodyPage();

}
