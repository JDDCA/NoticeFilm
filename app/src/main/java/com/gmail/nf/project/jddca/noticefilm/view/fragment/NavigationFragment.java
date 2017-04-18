package com.gmail.nf.project.jddca.noticefilm.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import com.gmail.nf.project.jddca.noticefilm.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Yaroslav Lutsenko on 14.04.2017.
 * Фрагмент для NavigationTabBar
 */

public class NavigationFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.bottom_navigation) BottomNavigationView bnv;
    private Unbinder unbinder;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_navigation_button_menu, container, false);
        unbinder = ButterKnife.bind(this,v);
        bnv.setOnNavigationItemSelectedListener(this);
        return v;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /** Метод который устанавливает логику для слушателя*/
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){}
        return true;
    }
}
