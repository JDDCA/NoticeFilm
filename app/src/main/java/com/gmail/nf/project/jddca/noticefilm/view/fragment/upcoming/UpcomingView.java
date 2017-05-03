package com.gmail.nf.project.jddca.noticefilm.view.fragment.upcoming;


import com.gmail.nf.project.jddca.noticefilm.model.pojos.Film;
import com.gmail.nf.project.jddca.noticefilm.view.IView;

import java.util.List;

public interface UpcomingView extends IView {

    void showMovieList(List<Film> movies);

}
