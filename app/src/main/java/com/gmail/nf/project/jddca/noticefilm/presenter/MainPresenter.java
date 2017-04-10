package com.gmail.nf.project.jddca.noticefilm.presenter;

import com.gmail.nf.project.jddca.noticefilm.model.firebase.FirebaseManager;
import com.gmail.nf.project.jddca.noticefilm.view.LoginActivity;
import com.gmail.nf.project.jddca.noticefilm.view.MainActivity;

import javax.inject.Inject;

import lombok.Setter;
import lombok.experimental.PackagePrivate;


/**
 * Created by Yaroslav Lutsenko on 07.04.2017.
 * It's Presenter for MainActivity
 * @see MainActivity
 */

@PackagePrivate @Setter
public class MainPresenter {


    MainActivity activity;

    FirebaseManager firebaseManager;

    @Inject public MainPresenter(FirebaseManager firebaseManager) {
        this.firebaseManager = firebaseManager;
    }

    public void checkSignIn (){
        if (!firebaseManager.checkSession()){
            activity.startActivity(LoginActivity.createIntent(activity));
            activity.finish();
        }
    }

    public void logOutGoogle(){
        firebaseManager.logOutGoogle(activity);
    }
    public void logOutAnonymously(){
        firebaseManager.logOutAnonymously();
        activity.startActivity(LoginActivity.createIntent(activity));
        activity.finish();
    }

    public boolean isAnonymous (){
        return firebaseManager.isAnonymusUser();
    }

    public void convertToGoogle(){
        firebaseManager.convert(activity,activity.getIntent().getStringExtra("token"));

    }

}
