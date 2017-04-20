package com.gmail.nf.project.jddca.noticefilm.model;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.gmail.nf.project.jddca.noticefilm.R;
import com.gmail.nf.project.jddca.noticefilm.model.utils.FirebaseUtils;
import com.gmail.nf.project.jddca.noticefilm.model.utils.RetrofitUtils;
import com.gmail.nf.project.jddca.noticefilm.view.MovieView;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.GenerateFragment;

import lombok.NonNull;

/**
 * Created by Yaroslav Lutsenko on 12.04.2017.
 * Manager для работы с данными.
 * Singleton класс который создаеться в Application
 *
 * @see com.gmail.nf.project.jddca.noticefilm.app.App
 */

public class DataManager {

    // класс синглитон
    private static DataManager instance;

    // utils для работы с Firebase
    private FirebaseUtils firebaseUtils;
    private RetrofitUtils retrofitUtils;

    private DataManager() {
        firebaseUtils = new FirebaseUtils();
        retrofitUtils = new RetrofitUtils();
    }

    /**
     * Метода для анонимного входа и через Google
     */
    public void login(@NonNull Fragment fragment, int PROVIDER) {
        switch (PROVIDER) {
            case FirebaseUtils.GOOGLE_PROVIDER:
                firebaseUtils.loginGoogle(fragment);
                break;
            case FirebaseUtils.ANONYMOUSLY_PROVIDER:
                firebaseUtils.loginAnonymously(fragment);
                break;
        }
    }


    public void checkResultSigned(@NonNull Fragment fragment, int resultCode, @NonNull Intent data) {
        firebaseUtils.checkResultSigned(fragment, resultCode, data);
    }


    /**
     * Instance класса синглитона
     */
    public static final DataManager getInstance() {
        if (instance != null) {
            return instance;
        }
        return new DataManager();
    }


    public void logout(@NonNull FragmentActivity activity, int PROVIDER) {
        switch (PROVIDER) {
            case FirebaseUtils.GOOGLE_PROVIDER:
                firebaseUtils.logoutGoogle(activity);
                break;
            case FirebaseUtils.ANONYMOUSLY_PROVIDER:
                firebaseUtils.logoutAnonymously(activity);
                break;
        }
    }

    public void getRandomFilm(MovieView view, Context context) {
        retrofitUtils.downloadRandomFilm(view,getApiKey(context),getLocales(context));
    }

    public void getFilm(MovieView view, Context context, Integer id) {
        retrofitUtils.downloadGenreFilm(view,getApiKey(context),getLocales(context),id);
    }

    private String getLocales (Context context){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N)
            return context.getResources().getConfiguration().locale.toString();
        return context.getResources().getConfiguration().getLocales().get(0).toString();
    }

    private String getApiKey (Context context){
        return context.getResources().getString(R.string.key);
    }

    public void initGenres(GenerateFragment view, Context context) {
        retrofitUtils.initGenreForSpinner (view,getApiKey(context),getLocales(context));
    }


}
