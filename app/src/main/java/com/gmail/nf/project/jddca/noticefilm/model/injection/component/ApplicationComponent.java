package com.gmail.nf.project.jddca.noticefilm.model.injection.component;

import android.content.Context;

import com.gmail.nf.project.jddca.noticefilm.model.firebase.FirebaseManager;
import com.gmail.nf.project.jddca.noticefilm.model.injection.module.ApplicationModule;
import com.gmail.nf.project.jddca.noticefilm.model.injection.module.FirebaseModule;
import com.gmail.nf.project.jddca.noticefilm.model.injection.scope.ApplicationScope;

import dagger.Component;

/**
 * Created by Yaroslav Lutsenko on 07.04.2017.
 */

@ApplicationScope
@Component (modules = {ApplicationModule.class, FirebaseModule.class})
public interface ApplicationComponent {

    Context context();
    FirebaseManager firebaseManager ();
    ActivityComponent.Builder activityComponentBuilder ();

}
