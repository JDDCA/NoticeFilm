package com.gmail.nf.project.jddca.noticefilm.model.firebase;

import android.app.Activity;
import android.content.Intent;
import android.database.Observable;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.gmail.nf.project.jddca.noticefilm.model.firebase.auth.AuthHelper;
import com.gmail.nf.project.jddca.noticefilm.model.injection.scope.ApplicationScope;
import com.gmail.nf.project.jddca.noticefilm.view.LoginActivity;
import com.gmail.nf.project.jddca.noticefilm.view.MainActivity;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import javax.inject.Inject;

import lombok.NonNull;
import lombok.experimental.PackagePrivate;

/**
 * Created by Yaroslav Lutsenko on 07.04.2017.
 */

@PackagePrivate
@ApplicationScope
public class FirebaseManager {

    AuthHelper authHelper;

    @Inject
    public FirebaseManager(AuthHelper authHelper) {
        this.authHelper = authHelper;
    }


    public void loginGoogle(@NonNull Activity activity) {
        if (!checkSession()) {
            activity.startActivityForResult(authHelper.getGoogleIntent(), AuthHelper.RC_SIGN_IN);
        }
    }

    public void loginAnonymously(@NonNull Activity activity) {
        if (!checkSession()) {
            authHelper.getAnonymouslyAuthResultTask().addOnCompleteListener(activity,task -> {
                if (task.isSuccessful()){
                    task.getResult().getUser().getToken(true).addOnCompleteListener(task1 -> {
                        activity.startActivity(MainActivity.createIntent(activity,task1.getResult().getToken()));
                        activity.finish();});
                }else Toast.makeText(activity,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
            });
        }
    }

    public  void logOutGoogle (@NonNull FragmentActivity fragmentActivity){
        authHelper.signOutGoogle(fragmentActivity).addOnCompleteListener(v -> {
            fragmentActivity.startActivity(LoginActivity.createIntent(fragmentActivity));
            fragmentActivity.finish();
        });
    }

    public void logOutAnonymously (){
        authHelper.signOutAnonymously();
    }

    public Integer getResultError(@NonNull Intent data) {
        IdpResponse response = authHelper.getResponse(data);
        if (response != null) {
            switch (response.getErrorCode()) {
                case ErrorCodes.NO_NETWORK:
                    return ErrorCodes.NO_NETWORK;
                case ErrorCodes.UNKNOWN_ERROR:
                    return ErrorCodes.UNKNOWN_ERROR;
            }
        }
        return null;
    }

    /**
     * Checks user's session
     * @return true if session created and false if didn't
     * */
    public boolean checkSession (){
        return authHelper.checkIsPreviousSession();
    }

    public boolean isAnonymusUser (){
        return authHelper.getUser().isAnonymous();
    }

    public void convert (Activity activity, String token){
        activity.startActivityForResult(authHelper.getGoogleIntent(), AuthHelper.RC_SIGN_IN);
        AuthCredential credential = GoogleAuthProvider.getCredential(token, null);
        authHelper.getUser().linkWithCredential(credential).addOnCompleteListener(task -> {
           Toast.makeText(activity,"CONVERT",Toast.LENGTH_SHORT).show();
        });
    }

    public String getToken(Intent data) {
        return authHelper.getResponse(data).getIdpToken();
    }
}

