package com.gmail.nf.project.jddca.noticefilm.model.injection.module;

import android.app.Application;
import android.content.Context;

import com.gmail.nf.project.jddca.noticefilm.model.injection.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;
import lombok.NonNull;
import lombok.experimental.PackagePrivate;

/**
 * Created by Yaroslav Lutsenko on 07.04.2017.
 * Provide application - level dependencies
 */

@Module
@PackagePrivate
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationScope
    Context provideContext() {
        return mApplication;
    }

}
