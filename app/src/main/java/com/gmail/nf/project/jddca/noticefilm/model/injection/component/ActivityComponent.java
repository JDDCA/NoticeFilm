package com.gmail.nf.project.jddca.noticefilm.model.injection.component;

import com.gmail.nf.project.jddca.noticefilm.model.injection.module.PresenterModule;
import com.gmail.nf.project.jddca.noticefilm.model.injection.scope.ScreenScope;
import com.gmail.nf.project.jddca.noticefilm.view.LoginActivity;

import dagger.Subcomponent;

/**
 * Created by Yaroslav Lutsenko on 08.04.2017.
 */

@Subcomponent (modules = {PresenterModule.class})
@ScreenScope
public interface ActivityComponent {

    @Subcomponent.Builder
    interface Builder {
        ActivityComponent.Builder presenterModule (PresenterModule presenterModule);
        ActivityComponent build();
    }

    void inject (LoginActivity activity);



}
