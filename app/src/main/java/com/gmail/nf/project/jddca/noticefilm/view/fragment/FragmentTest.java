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
 * фрагмент для теста
 */

public class FragmentTest extends Fragment {

    private String title;
    private int page;
    Button testBtn;

    //метод для инициализации фрагмента
    public static Fragment newInctace(int page, String title){
        FragmentTest fm = new FragmentTest();
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
        View v = inflater.inflate(R.layout.fragment_test, container, false);

        testBtn = (Button)v.findViewById(R.id.test_button);
        testBtn.setText(title);
        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Hello, I'm page " + page, Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}
