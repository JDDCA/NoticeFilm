package com.gmail.nf.project.jddca.noticefilm.view.activity;


import android.app.Activity;

public interface Login {

    Activity getAct();
    void toLog (String s);
    void showError (Throwable throwable);
}
