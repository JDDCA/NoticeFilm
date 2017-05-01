package com.gmail.nf.project.jddca.noticefilm.model.db;


import com.gmail.nf.project.jddca.noticefilm.model.pojos.Film;

public interface Database {

    void saveToFavoritesMovie(Film film);

    void removeFavoritesMovie(Film film);

    void saveToListMovie(Film film);

    void removeListsMovie(Film film);


}
