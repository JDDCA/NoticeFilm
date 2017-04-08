package com.gmail.nf.project.jddca.noticefilm.app;

import com.gmail.nf.project.jddca.noticefilm.model.injection.component.ActivityComponent;
import com.gmail.nf.project.jddca.noticefilm.model.injection.component.ApplicationComponent;
import com.gmail.nf.project.jddca.noticefilm.model.injection.component.DaggerApplicationComponent;
import com.gmail.nf.project.jddca.noticefilm.model.injection.module.ApplicationModule;
import com.gmail.nf.project.jddca.noticefilm.model.injection.module.PresenterModule;
import com.gmail.nf.project.jddca.noticefilm.model.injection.scope.ApplicationScope;

import lombok.experimental.PackagePrivate;

/**
 * Created by Yaroslav Lutsenko on 07.04.2017.
 * This class inits Dagger 2
 */

@ApplicationScope
@PackagePrivate
public class App extends android.app.Application{

    private static App instanse;

    ApplicationComponent applicationComponent;
    ActivityComponent activityComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instanse = this;
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent(){
        return applicationComponent;
    }

    public ActivityComponent getActivityComponent (){
        if (activityComponent==null) {
            activityComponent = applicationComponent.activityComponentBuilder()
                    .presenterModule(new PresenterModule())
                    .build();
        }
        return activityComponent;
    }

    public static App getInstanse (){
        return instanse;
    }

}


