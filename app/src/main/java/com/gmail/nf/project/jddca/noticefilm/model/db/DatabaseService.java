package com.gmail.nf.project.jddca.noticefilm.model.db;


import com.gmail.nf.project.jddca.noticefilm.model.pojos.Film;

import static com.gmail.nf.project.jddca.noticefilm.model.db.DatabaseState.ErrorFromDatabase;
import static com.gmail.nf.project.jddca.noticefilm.model.db.DatabaseState.ResultFromDatabase;

public interface DatabaseService {

    void saveToFavoritesMovie(Film film);

    void removeFavoritesMovie(Film film);

    void saveToListMovie(Film film);

    void removeListsMovie(Film film);

    void checkInFavoritesMovie (Film film, ResultFromDatabase resultFromDatabase, ErrorFromDatabase errorFromDatabase);

    void checkInListMovie (Film film, ResultFromDatabase resultFromDatabase, ErrorFromDatabase errorFromDatabase);


}
