package com.gmail.nf.project.jddca.noticefilm.app;

import android.support.multidex.MultiDexApplication;

import lombok.experimental.PackagePrivate;

/**
 * Created by Yaroslav Lutsenko on 07.04.2017.
 * Класс который расширяет Activity
 * @version 2.0 подключение и использование Dagger зависимостей при создании приложения
 *
 */

@PackagePrivate
public class App extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

}


