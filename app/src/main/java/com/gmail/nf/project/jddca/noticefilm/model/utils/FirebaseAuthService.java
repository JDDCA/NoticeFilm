package com.gmail.nf.project.jddca.noticefilm.model.utils;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.ResultCodes;
import com.gmail.nf.project.jddca.noticefilm.R;
import com.gmail.nf.project.jddca.noticefilm.model.pojos.Film;
import com.gmail.nf.project.jddca.noticefilm.view.activity.LoginActivity;
import com.gmail.nf.project.jddca.noticefilm.view.activity.MainActivity;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Yaroslav Lutsenko on 12.04.2017.
 *
 * @version 1.0. - {utils для работы с Firebase через библиотеку FirebaseUI и частичная работа без этой библиотеки}
 *          Включает в себя методы работы с авторизацией, и бд.
 */

public class FirebaseAuthService {

    /**
     * статическая переменная для @code {requestCode при отправки startActivityForResult} при авторизации пользователя
     */
    public static final int RC_SIGN_IN = 0b11001000001; //1601

    private static FirebaseAuth getAuth (){
        return FirebaseAuth.getInstance();
    }

    public static FirebaseUser getCurrentUser (){
        return getAuth().getCurrentUser();
    }



    /**
     * Метода проверяет был ли вход пользователя ранее
     * @return false если пользователь еще не авторизировался в системе.
     */
    public static boolean checkSession() {
        return getCurrentUser() != null;
    }

    /**
     * Проверяет аннонимная учетная запись пользователя или нет.
     * @throws NullPointerException
     */
    public static boolean isAnonymousUser() {
        if (!checkSession())
            throw new NullPointerException("isAnonymousUser: user have not signed yet!");
        return getCurrentUser().isAnonymous();
    }

    public Task <AuthResult> getAuthResultTask (){
        return getAuth().signInAnonymously();
    }

    /**
     * Метод возвращает Intent для вызова авторизации пользователя через GoogleProvider
     * @return Intent
     */
    public Intent getGoogleIntent() {
        return AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setIsSmartLockEnabled(false)
                .setProviders(Collections.singletonList(new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                .build();
    }

    public void logoutGoogle(@NonNull FragmentActivity activity) {
        AuthUI.getInstance()
                .signOut(activity)
                .addOnCompleteListener(task -> {
                    activity.startActivity(LoginActivity.createIntent(activity));
                    activity.finish();
                });
    }

    public static void logoutAnonymously(@NonNull Activity activity) {
        getAuth().signOut();
        activity.startActivity(MainActivity.createIntent(activity));
        activity.finish();
    }
}
