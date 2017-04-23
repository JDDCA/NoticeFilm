package com.gmail.nf.project.jddca.noticefilm.model.utils;


import android.content.Context;
import android.os.Build;

import com.gmail.nf.project.jddca.noticefilm.R;

public class ApiService {

    public static String getLocales(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N)
            return context.getResources().getConfiguration().locale.toString();
        return context.getResources().getConfiguration().getLocales().get(0).toString();
    }

    public static String getApiKey(Context context) {
        return context.getResources().getString(R.string.key);
    }

}
