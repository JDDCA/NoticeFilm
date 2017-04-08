package com.gmail.nf.project.jddca.noticefilm.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gmail.nf.project.jddca.noticefilm.R;
import com.gmail.nf.project.jddca.noticefilm.presenter.DialogFactory;

public class LoginFragment extends Fragment {

    private Button mButtonLogin;
    private static final String DIALOG_MESSAGE = "dialogMessage";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_login, container, false);

        mButtonLogin = (Button)v.findViewById(R.id.button_login);
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                DialogFragment dialog = DialogFactory.newInstance(R.string.dialog_authorisation_error_title);
                dialog.show(fm, DIALOG_MESSAGE);
            }
        });


        return v;
    }
}
