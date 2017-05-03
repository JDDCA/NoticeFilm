package com.gmail.nf.project.jddca.noticefilm.model.db;


import com.gmail.nf.project.jddca.noticefilm.model.pojos.Film;
import com.google.firebase.database.DatabaseReference;

import static com.gmail.nf.project.jddca.noticefilm.model.db.DatabaseState.ErrorFromDatabase;
import static com.gmail.nf.project.jddca.noticefilm.model.db.DatabaseState.ResultFromDatabase;

public interface DatabaseService {



    void saveToListMovie(Film film);

    void removeListsMovie(Film film);

    void checkInListMovie (Film film, ResultFromDatabase resultFromDatabase, ErrorFromDatabase errorFromDatabase);

    DatabaseReference getRefList();
}
