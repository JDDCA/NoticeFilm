package com.gmail.nf.project.jddca.noticefilm.view.fragment.navigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import com.gmail.nf.project.jddca.noticefilm.R;
import com.gmail.nf.project.jddca.noticefilm.view.activity.MainActivity;
import com.gmail.nf.project.jddca.noticefilm.view.activity.SingleFragmentActivity;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.generate.GenerateFragmentImpl;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.list.ListFragmentImpl;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.login.LoginFragmentImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Yaroslav Lutsenko on 14.04.2017.
 * Фрагмент для NavigationTabBar
 */

public class NavigationFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {

    public static final String TAG_NAVIGATION = "navigation_tag";

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bnv;
    private Unbinder unbinder;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_navigation_button_menu, container, false);
        unbinder = ButterKnife.bind(this, v);
        bnv.setOnNavigationItemSelectedListener(this);
        return v;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * Метод который устанавливает логику для слушателя
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment navFrag = fragmentManager.findFragmentByTag(TAG_NAVIGATION);
        Fragment genFrag = fragmentManager.findFragmentByTag(GenerateFragmentImpl.GENERATE_TAG);
        Fragment listFrag = fragmentManager.findFragmentByTag(ListFragmentImpl.LIST_TAG);
        switch (item.getItemId()) {
            case R.id.action_generate:
                Log.i("TEMP", "action_generate: " + navFrag);
                Log.i("TEMP", "action_generate: " + genFrag);
                Log.i("TEMP", "action_generate: " + listFrag);
                if (listFrag != null) {
                    Log.i("TEMP", "listFrag != null: " + navFrag);
                    Log.i("TEMP", "listFrag != null: " + genFrag);
                    Log.i("TEMP", "listFrag != null: " + listFrag);
                    fragmentManager
                            .beginTransaction()
                            .remove(listFrag)
                            .add(R.id.fragment_container, new GenerateFragmentImpl(), GenerateFragmentImpl.GENERATE_TAG)
                            .addToBackStack(null)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .commit();
                    return true;
                }
//                getFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.fragment_container, new GenerateFragmentImpl())
//                        .replace(R.id.fragment_container, this)
//                        .addToBackStack(null)
//                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                        .commit();
//                return true;
            case R.id.action_list:
                Log.i("TEMP", "action_list: " + navFrag);
                Log.i("TEMP", "action_list: " + genFrag);
                Log.i("TEMP", "action_list: " + listFrag);
                if (genFrag != null) {
                    Log.i("TEMP", "genFrag != null: " + navFrag);
                    Log.i("TEMP", "genFrag != null: " + genFrag);
                    Log.i("TEMP", "genFrag != null: " + listFrag);
                    fragmentManager
                            .beginTransaction()
                            .remove(genFrag)
                            .add(R.id.fragment_container, new ListFragmentImpl(), ListFragmentImpl.LIST_TAG)
                            .addToBackStack(null)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .commit();
                    return true;
                }


//                getFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.fragment_container, new LoginFragmentImpl())
//                        .replace(R.id.fragment_container, this)
//                        .addToBackStack(null)
//                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                        .commit();
//                return true;
        }
        return true;
    }
}
