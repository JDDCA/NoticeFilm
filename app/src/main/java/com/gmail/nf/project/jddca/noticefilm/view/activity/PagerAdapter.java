package com.gmail.nf.project.jddca.noticefilm.view.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.gmail.nf.project.jddca.noticefilm.view.fragment.FragmentTest;

/**
  PagerAdapter пока все по хардкоду))
 Фрагменты прикручиваются через newInstance или подобный метод
 */

public class PagerAdapter extends FragmentPagerAdapter {

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return FragmentTest.newInctace(1, "Random Movie");
            case 1:
                return FragmentTest.newInctace(2, "Favorite movie");
            case 2:
                return FragmentTest.newInctace(3, "Play movie");
            case 3:
                return FragmentTest.newInctace(4, "Profile");
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
