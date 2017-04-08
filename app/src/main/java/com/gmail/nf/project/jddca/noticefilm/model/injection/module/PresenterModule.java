package com.gmail.nf.project.jddca.noticefilm.model.injection.module;

import com.gmail.nf.project.jddca.noticefilm.model.firebase.FirebaseManager;
import com.gmail.nf.project.jddca.noticefilm.model.injection.scope.ScreenScope;
import com.gmail.nf.project.jddca.noticefilm.presenter.LoginPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Yaroslav Lutsenko on 08.04.2017.
 */

@Module
@ScreenScope
public class PresenterModule {

    @Provides
    LoginPresenter loginPresenter (FirebaseManager manager){
        return new LoginPresenter(manager);
    }
}
