package com.gmail.nf.project.jddca.noticefilm.view;


import com.gmail.nf.project.jddca.noticefilm.model.pojos.Genre;

import java.util.List;

public interface MovieViewGenerate extends MovieView {

    void addGenre(List<Genre> genres);
}
