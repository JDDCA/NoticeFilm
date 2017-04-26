package com.gmail.nf.project.jddca.noticefilm.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.gmail.nf.project.jddca.noticefilm.R;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.navigation.NavigationFragment;

/**
 * Базовый класс для Activity, помогает создать хост с 1 фрагментом
 * Base class for Activity, help with fragment host creating
 */

public abstract class SingleFragmentActivity extends FragmentActivity {

    /**
     * Метод для загрузки основного фрагмента активити
     */
    protected abstract Fragment createFragment();

    /**
     * Метод для загрузки Button Navigation
     *
     * @return {@code true} - если необходимо загрузить фрагмент с Button Navigation
     * {@code false} - если не нужно загружать фрагмент с Button Navigation
     */
    protected abstract boolean createButtonNavigationFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = createFragment();
            if (!createButtonNavigationFragment()) {
                fm.beginTransaction()
                        .add(R.id.fragment_container, fragment)
                        .commit();
            } else {
                fm.beginTransaction()
                        .add(R.id.fragment_container, fragment)
                        .add(R.id.fragment_container, new NavigationFragment())
                        .commit();
            }
        }
    }

}
