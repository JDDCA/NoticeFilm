package com.gmail.nf.project.jddca.noticefilm.view.fragment.generate;


import android.view.View;

import com.gmail.nf.project.jddca.noticefilm.model.pojos.Film;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.context.ContextFragment;

import java.util.List;

public interface GenerateFragment extends ContextFragment{

    View getProgressBarView ();
    View getDefaultView ();
    void showError (Throwable throwable);
    void showFilm (Film film);
    void toLog (String s);
    void updateGenres(List<String> stringList);
    void showAuthDialog ();

}
