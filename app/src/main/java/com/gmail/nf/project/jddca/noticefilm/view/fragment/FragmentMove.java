package com.gmail.nf.project.jddca.noticefilm.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.gmail.nf.project.jddca.noticefilm.R;

/**
 * 2й фрагмент для теста(аналогичен первому);
 */

public class FragmentMove extends Fragment {

    private String title;
    private int page;
    private Button bn;

    public static Fragment newInctace(int page, String title){
        FragmentMove fm = new FragmentMove();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fm.setArguments(args);
        return fm;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.test_fragment, container, false);

        bn = (Button)v.findViewById(R.id.button_for_test);
        bn.setText(title);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Hello, I'm page " + page, Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }
}
