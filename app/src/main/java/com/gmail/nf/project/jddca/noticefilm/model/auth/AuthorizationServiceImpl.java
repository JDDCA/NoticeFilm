package com.gmail.nf.project.jddca.noticefilm.model.auth;


import android.content.Intent;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Collections;

public class AuthorizationServiceImpl implements AuthorizationService {



    @Override
    public FirebaseUser getUser() {
        return getAuth().getCurrentUser();
    }

    @Override
    public Intent getSingleGoogleIntent() {
        return AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setIsSmartLockEnabled(false)
                .setProviders(Collections.singletonList(new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                .build();
    }


    private FirebaseAuth getAuth (){
        return FirebaseAuth.getInstance();
    }
}
