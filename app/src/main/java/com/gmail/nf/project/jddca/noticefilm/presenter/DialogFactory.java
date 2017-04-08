package com.gmail.nf.project.jddca.noticefilm.presenter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

public class DialogFactory extends DialogFragment{

    private static final String DIALOG_ERROR = "dialog";

    public static DialogFragment newInstance(int title){
        DialogFragment fragment = new DialogFactory();
        Bundle args = new Bundle();
        args.putInt(DIALOG_ERROR, title);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt(DIALOG_ERROR);

        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }
}
