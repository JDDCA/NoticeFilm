package com.gmail.nf.project.jddca.noticefilm.model.utils;


import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;

public class PreferencesService {

    public static String GENRES = "com.gmail.nf.project.jddca.noticefilm.model.utils.genres";

    public static void saveGenres (Activity activity, String gson){
        activity
                .getPreferences(Activity.MODE_PRIVATE)
                .edit()
                .putString(GENRES,gson)
                .apply();
    }

    public static void clearGenres (Activity activity){
        activity
                .getPreferences(Activity.MODE_PRIVATE)
                .edit()
                .remove(GENRES);
    }

    public static boolean isGeneres (Activity activity){
        return activity
                .getPreferences(Activity.MODE_PRIVATE)
                .contains(GENRES);
    }


}
