package com.gmail.nf.project.jddca.noticefilm.presenter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;

import com.gmail.nf.project.jddca.noticefilm.R;

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

    public static Dialog createDialogMT (@NonNull Context context, @NonNull String message, @NonNull String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(message)
                .setMessage(title)
                .setPositiveButton(R.string.ok, null);
        return builder.create();
    }

    public static Dialog createDialogMT (@NonNull Context context, @NonNull @StringRes int message, @NonNull @StringRes int title){
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(message)
                .setMessage(title)
                .setPositiveButton(R.string.ok,null);
        return builder.create();

    }
}
