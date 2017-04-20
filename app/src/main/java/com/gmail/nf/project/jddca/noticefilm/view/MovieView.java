package com.gmail.nf.project.jddca.noticefilm.view;


import com.gmail.nf.project.jddca.noticefilm.model.pojos.Film;

public interface MovieView {

    void showMovie(Film film);

    void showError(Throwable throwable);

}
