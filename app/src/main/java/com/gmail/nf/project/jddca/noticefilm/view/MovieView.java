package com.gmail.nf.project.jddca.noticefilm.view;


import com.gmail.nf.project.jddca.noticefilm.model.pojos.Movie;

public interface MovieView extends IView {

    void showMovie(Movie movie);

    void showError();

}
