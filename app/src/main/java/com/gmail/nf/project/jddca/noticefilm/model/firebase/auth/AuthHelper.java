package com.gmail.nf.project.jddca.noticefilm.model.firebase.auth;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Arrays;


import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.PackagePrivate;

/**
 * Created by Yaroslav Lutsenko on 07.04.2017.
 * Helps with sign in and log out users
 */

@PackagePrivate @Setter
public class AuthHelper {

    public static final int RC_SIGN_IN = 1;

    FirebaseAuth auth;

    public AuthHelper(FirebaseAuth auth) {
        this.auth = auth;
    }

    /**
     * Before invoking the FirebaseUI authentication flow,
     * your app should check whether a user is already signed in from a previous session
     * @return true - already signed in or false - not signed in
     *
     * */
    public boolean checkIsPreviousSession (){
        return auth.getCurrentUser()!=null;
    }

    /**
     * Crete Intent for sign in
     * @return Intent with only GOOGLE_PROVIDER and SmartLock
     * */
    public Intent getGoogleIntent (){
        return AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setIsSmartLockEnabled(true)
                .setProviders(Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                .build();
    }

    /**
     * Extract the {@link IdpResponse} from the flow's result intent.
     * @return IdpResponse
     * @see IdpResponse#fromResultIntent(Intent)
     * */
    public IdpResponse getResponse (Intent data){return  IdpResponse.fromResultIntent(data);}

    public Task<AuthResult> getAnonymouslyAuthResultTask (){
        return auth.signInAnonymously();
    }

    public Task<Void> signOutGoogle (@NonNull FragmentActivity fragmentActivity){
        return AuthUI.getInstance().signOut(fragmentActivity);
    }
    public void signOutAnonymously (){
        auth.signOut();
    }
    public FirebaseUser getUser (){return auth.getCurrentUser();}

}
