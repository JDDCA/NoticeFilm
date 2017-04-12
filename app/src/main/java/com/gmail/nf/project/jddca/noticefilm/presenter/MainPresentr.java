package com.gmail.nf.project.jddca.noticefilm.presenter;

import android.content.Intent;

import com.gmail.nf.project.jddca.noticefilm.model.DataManager;
import com.gmail.nf.project.jddca.noticefilm.view.activity.MainActivity;

import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.PackagePrivate;

@PackagePrivate
public class MainPresentr {

    DataManager dataManager;
    @Setter
    MainActivity activity;

    public MainPresentr() {
        dataManager = DataManager.getInstance();
    }

    public void logout (int PROVIDER){
            dataManager.logout(activity,PROVIDER);
    }


}
