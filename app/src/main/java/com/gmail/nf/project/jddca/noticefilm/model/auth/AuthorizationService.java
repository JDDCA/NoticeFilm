package com.gmail.nf.project.jddca.noticefilm.model.auth;


import android.content.Intent;

import com.google.firebase.auth.FirebaseUser;

public interface AuthorizationService {

    int AUTH_RESULT = 0b11001000001; //1601;

    FirebaseUser getUser();

    Intent getSingleGoogleIntent();

}
