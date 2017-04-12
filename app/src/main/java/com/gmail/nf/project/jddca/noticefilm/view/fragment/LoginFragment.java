package com.gmail.nf.project.jddca.noticefilm.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.nf.project.jddca.noticefilm.R;
import com.google.android.gms.common.SignInButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Yaroslav Lutsenko on 12.04.2017.
 * Класс для создания login fragment
 */

public class MainFragment extends Fragment {


    @BindView(R.id.signInGoogle) SignInButton signInButton;
    @BindView(R.id.signInAnonymously) AppCompatButton signInAnonymously;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment,container,false);
        unbinder = ButterKnife.bind(this,view);
        signInButton.setOnClickListener(v -> {});
        signInButton.setOnClickListener(v -> {});
        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
