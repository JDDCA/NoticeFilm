package com.gmail.nf.project.jddca.noticefilm.view;


import com.gmail.nf.project.jddca.noticefilm.model.pojos.Film;
import com.gmail.nf.project.jddca.noticefilm.model.pojos.UpcomingMovie;

import java.util.List;

public interface UpcomingView extends IView {

    void showUpcomingMoviesList(List<Film> movies);

}
