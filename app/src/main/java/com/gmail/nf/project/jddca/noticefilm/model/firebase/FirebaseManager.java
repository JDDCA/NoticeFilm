package com.gmail.nf.project.jddca.noticefilm.model.firebase;

import android.app.Activity;
import android.content.Intent;
import android.database.Observable;
import android.widget.Toast;

import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.gmail.nf.project.jddca.noticefilm.model.firebase.auth.AuthHelper;
import com.gmail.nf.project.jddca.noticefilm.model.injection.scope.ApplicationScope;
import com.gmail.nf.project.jddca.noticefilm.view.MainActivity;

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
                    activity.startActivity(MainActivity.createIntent(activity));
                    activity.finish();
                }else Toast.makeText(activity,"ERROR!!!",Toast.LENGTH_SHORT).show();
            });
        }
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




}

