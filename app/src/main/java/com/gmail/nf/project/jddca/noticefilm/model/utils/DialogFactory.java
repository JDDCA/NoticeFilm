package com.gmail.nf.project.jddca.noticefilm.model.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.gmail.nf.project.jddca.noticefilm.R;

/**
 * Класс для создания диалоговых окон
 */
public class DialogFactory extends DialogFragment {

    private static final String DIALOG_TITLE = "dialog_title";
    private static final String DIALOG_MESSAGE = "dialog_message";
    public static final String DIALOG_ERROR = "dialog_error";


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
        DialogFragment fragment = new DialogFactory();
        Bundle args = new Bundle();
        args.putInt(DIALOG_TITLE, title);
        args.putInt(DIALOG_MESSAGE, message);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt(DIALOG_TITLE);
        int message = getArguments().getInt(DIALOG_MESSAGE);
        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, null)
                .create();
    }

}
