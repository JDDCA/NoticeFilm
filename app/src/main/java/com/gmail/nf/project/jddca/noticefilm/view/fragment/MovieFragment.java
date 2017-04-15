package com.gmail.nf.project.jddca.noticefilm.view.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.nf.project.jddca.noticefilm.R;

import butterknife.BindView;
import lombok.Setter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    public MovieFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

}
