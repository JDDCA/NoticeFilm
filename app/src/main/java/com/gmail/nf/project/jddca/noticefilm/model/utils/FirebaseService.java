package com.gmail.nf.project.jddca.noticefilm.model.utils;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.ResultCodes;
import com.gmail.nf.project.jddca.noticefilm.R;
import com.gmail.nf.project.jddca.noticefilm.view.activity.LoginActivity;
import com.gmail.nf.project.jddca.noticefilm.view.activity.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

/**
 * Created by Yaroslav Lutsenko on 12.04.2017.
 *
 * @version 1.0. - {utils для работы с Firebase через библиотеку FirebaseUI и частичная работа без этой библиотеки}
 *          Включает в себя методы работы с авторизацией, и бд.
 */

public class FirebaseService {

    /**
     * статическая переменная для @code {requestCode при отправки startActivityForResult} при авторизации пользователя
     */
    public static final int RC_SIGN_IN = 0b11001000001; //1601
    public static final int GOOGLE_PROVIDER = 0b11001000010; //1602
    public static final int ANONYMOUSLY_PROVIDER = 0b11001000011; //1603
    private String token;

    /**
     * Метода проверяет был ли вход пользователя ранее
     *
     * @return false если пользователь еще не авторизировался в системе.
     */
    public static boolean checkSession() {
        return FirebaseAuth.getInstance().getCurrentUser() != null;
    }

    /**
     * Проверяет аннонимная учетная запись пользователя или нет.
     *
     * @throws NullPointerException
     */
    public static boolean isAnonymousUser() {
        if (!checkSession())
            throw new NullPointerException("isAnonymousUser: user have not signed yet!");
        return FirebaseAuth.getInstance().getCurrentUser().isAnonymous();
    }

    /**
     * Метод возвращает Intent для вызова авторизации пользователя через GoogleProvider
     *
     * @return Intent
     */
    private Intent getGoogleIntent() {
        return AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setIsSmartLockEnabled(true)
                .setProviders(Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                .build();
    }

    /**
     * Метод для входа через Google+
     */
    public void loginGoogle(@NonNull Fragment fragment) {
        if (!checkSession()) {
            // not signed in
            fragment.startActivityForResult(getGoogleIntent(), RC_SIGN_IN);
        } else {
            // already signed in
            fragment.startActivity(MainActivity.createIntent(fragment.getActivity()));
            fragment.getActivity().finish();
        }
    }

    // TODO: 12.04.2017  Долго работает метод, нужна заставка

    /**
     * Метод аннонимного входа
     */
    public void loginAnonymously(@NonNull Fragment fragment) {
        FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener(fragment.getActivity(), task -> {
            if (task.isSuccessful()) {
//                task.getResult().getUser().getToken(true).addOnCompleteListener(fragment.getActivity(), task1 -> {
//                    if (task1.isSuccessful()) {
//                        String token = task1.getResult().getToken();
//                        if (token != null) {
//                            saveToken(token);
                fragment.startActivity(MainActivity.createIntent(fragment.getActivity()));
                fragment.getActivity().finish();
//                        }
//                    } else {
//                        DialogFactory.newInstance(R.string.error, R.string.dialog_network_error).show(
//                                fragment.getFragmentManager(), DialogFactory.DIALOG_ERROR);
//                    }
//                });
            } else {
                DialogFactory.newInstance(R.string.error, R.string.dialog_network_error).show(
                        fragment.getFragmentManager(), DialogFactory.DIALOG_ERROR);
            }
        });
    }

    /**
     * Метод проверяет результат входа пользователя и сохраняет токен при успешном входе.
     * При ошибке входа - выводит диологовые окна.
     */
    public void checkResultSigned(@NonNull Fragment fragment, int resultCode, @NonNull Intent data) {
        IdpResponse response = IdpResponse.fromResultIntent(data);
        if (resultCode != ResultCodes.OK) {
            // Sign in failed
            if (response == null) {
                // User pressed back button
                DialogFactory.newInstance(R.string.error, R.string.dialog_cancel_error)
                        .show(fragment.getFragmentManager(), DialogFactory.DIALOG_ERROR);
            }
            if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
                // Error Network
                DialogFactory.newInstance(R.string.error, R.string.dialog_network_error)
                        .show(fragment.getFragmentManager(), DialogFactory.DIALOG_ERROR);
            }
            if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                // UNKNOWN_ERROR
                DialogFactory.newInstance(R.string.error, R.string.dialog_unknown_error)
                        .show(fragment.getFragmentManager(), DialogFactory.DIALOG_ERROR);
            }
        } else {
            saveToken(response);
            fragment.startActivity(MainActivity.createIntent(fragment.getActivity()));
            fragment.getActivity().finish();
        }
    }


    private void saveToken(@NonNull IdpResponse response) {
        token = response.getIdpToken();
    }

    private void saveToken(@NonNull String token) {
        this.token = token;
    }

    /**
     * @return String token
     * @throws NullPointerException
     */
    private String getToken() {
        if (token != null)
            return token;
        throw new NullPointerException("FirebaseService: I haven't token!");
    }

    public void logoutGoogle(@NonNull FragmentActivity activity) {
        AuthUI.getInstance()
                .signOut(activity)
                .addOnCompleteListener(task -> {
                    activity.startActivity(LoginActivity.createIntent(activity));
                    activity.finish();
                });
    }

    public void logoutAnonymously(@NonNull FragmentActivity activity) {
        FirebaseAuth.getInstance().signOut();
        activity.startActivity(LoginActivity.createIntent(activity));
        activity.finish();
    }
}
