package com.gmail.nf.project.jddca.noticefilm.model;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.gmail.nf.project.jddca.noticefilm.model.utils.FirebaseUtils;
import com.gmail.nf.project.jddca.noticefilm.view.activity.MainActivity;

import lombok.NonNull;

/**
 * Created by Yaroslav Lutsenko on 12.04.2017.
 * Manager для работы с данными.
 * Singleton класс который создаеться в Application
 * @see com.gmail.nf.project.jddca.noticefilm.app.App
 */

public class DataManager {

    // класс синглитон
    static DataManager instance;

    // utils для работы с Firebase
    private FirebaseUtils firebaseUtils;

    private DataManager() {
        firebaseUtils = new FirebaseUtils();
    }

    /**Метода для анонимного входа и через Google */
    public void login (@NonNull Fragment fragment, int PROVIDER){
        switch (PROVIDER){
            case FirebaseUtils.GOOGLE_PROVIDER:
                firebaseUtils.loginGoogle(fragment);
                break;
            case FirebaseUtils.ANONYMOUSLY_PROVIDER:
                firebaseUtils.loginAnonymously(fragment);
                break;
        }
    }



    public void checkResultSigned(@NonNull Fragment fragment, int resultCode, @NonNull Intent data){
        firebaseUtils.checkResultSigned(fragment,resultCode,data);
    }


    /** Instance класса синглитона*/
    public static final DataManager getInstance (){
        if (instance != null){
            return instance;
        }
        return new DataManager();
    }


    public void logout(@NonNull FragmentActivity activity, int PROVIDER) {
        switch (PROVIDER){
            case FirebaseUtils.GOOGLE_PROVIDER:
                firebaseUtils.logoutGoogle(activity);
                break;
            case FirebaseUtils.ANONYMOUSLY_PROVIDER:
                firebaseUtils.logoutAnonymously(activity);
                break;
        }
    }

}
