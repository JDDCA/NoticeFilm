package com.gmail.nf.project.jddca.noticefilm.model.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import com.gmail.nf.project.jddca.noticefilm.R;

/**
 * Класс для создания диалоговых окон
 */
public class DialogFactory extends DialogFragment {

    private static final String DIALOG_TITLE = "dialog_title";
    private static final String DIALOG_MESSAGE = "dialog_message";
    public static final String DIALOG_ERROR = "dialog_error";
    private static final int DEFAULT_INT = 0;
    private static final String DEFAULT_TITLE = "Error";

    public static DialogFragment newInstance(int title) {
        DialogFragment fragment = new DialogFactory();
        Bundle args = new Bundle();
        args.putInt(DIALOG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Метод для создания начальных значений диалогового окна
     *
     * @param title   id титл
     * @param message id сообщения
     */
    public static DialogFragment newInstance(int title, int message) {
        boolean cancelable = true;
        if (title != DEFAULT_INT && message != DEFAULT_INT)
            cancelable = false;
        DialogFragment fragment = new DialogFactory();
        Bundle args = new Bundle();
        args.putInt(DIALOG_TITLE, title);
        args.putInt(DIALOG_MESSAGE, message);
        fragment.setCancelable(cancelable);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt(DIALOG_TITLE, DEFAULT_INT);
        int message = getArguments().getInt(DIALOG_MESSAGE, DEFAULT_INT);
        if (title != DEFAULT_INT && message != DEFAULT_INT) {
            if (title == R.string.error && message == R.string.dialog_network_error)
                return new AlertDialog.Builder(getActivity())
                        .setTitle(title)
                        .setMessage(message)
                        .setPositiveButton(R.string.ok, ((dialog, which) -> {
                            if (which < 0) {
                                if (!ApiService.isNetwork(getContext())) {
                                    DialogFactory.newInstance(R.string.error, R.string.dialog_network_error)
                                            .show(getFragmentManager(), DialogFactory.DIALOG_ERROR);
                                } else {
                                    dialog.dismiss();
                                }}}))
                        .create();
            if (title == R.string.info && message == R.string.auth_dialog)
                return new AlertDialog.Builder(getActivity())
                        .setTitle(title)
                        .setMessage(message)
                        .setPositiveButton(R.string.yes_do_it, (dialog, which) -> {
                            FirebaseService.logoutAnonymously(getActivity());
                        })
                        .setNegativeButton(R.string.not_do_it, null)
                        .create();
        }
        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, null)
                .create();
    }

}