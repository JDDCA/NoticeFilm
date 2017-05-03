package com.gmail.nf.project.jddca.noticefilm.model.utils;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

import com.gmail.nf.project.jddca.noticefilm.R;

import static android.content.Context.MODE_PRIVATE;

public class ApiService {

    public static String getLocales(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N)
            return context.getResources().getConfiguration().locale.toString();
        return context.getResources().getConfiguration().getLocales().get(0).toString();
    }

    public static String getApiKey(Context context) {
        return context.getResources().getString(R.string.key);
    }

    // TODO: 27.04.2017  Запрос для проверки
    public static boolean isNetwork(Context context){
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}
