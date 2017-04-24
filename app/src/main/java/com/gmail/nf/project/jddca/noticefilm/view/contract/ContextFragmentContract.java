package com.gmail.nf.project.jddca.noticefilm.view.contract;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

public interface ContextFragmentContract {
    Context getCntxt ();
    Fragment getFrgmnt ();
    Activity getActvt ();
    void startActvtFor (@NonNull Intent intent, int requestCode);
    void startActvt (@NonNull Intent intent);
    void finishActvt ();

}
