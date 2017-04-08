package com.gmail.nf.project.jddca.noticefilm.model.injection.module;

import com.gmail.nf.project.jddca.noticefilm.model.firebase.FirebaseManager;
import com.gmail.nf.project.jddca.noticefilm.model.firebase.auth.AuthHelper;
import com.gmail.nf.project.jddca.noticefilm.model.injection.scope.ApplicationScope;
import com.google.firebase.auth.FirebaseAuth;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Yaroslav Lutsenko on 07.04.2017.
 * Provide firebase dependencies
 */

@Module
@ApplicationScope
public class FirebaseModule {

    @Provides
    FirebaseAuth auth (){
        return FirebaseAuth.getInstance();
    }

    @Provides
    AuthHelper authHelper (FirebaseAuth auth){
        return new AuthHelper(auth);
    }

    @Provides
    FirebaseManager firebaseManager (AuthHelper authHelper ){
        return new FirebaseManager(authHelper);
    }


}
