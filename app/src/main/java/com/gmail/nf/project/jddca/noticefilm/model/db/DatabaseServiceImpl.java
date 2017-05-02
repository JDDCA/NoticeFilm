package com.gmail.nf.project.jddca.noticefilm.model.db;


import com.gmail.nf.project.jddca.noticefilm.model.db.DatabaseState.ResultFromDatabase;
import com.gmail.nf.project.jddca.noticefilm.model.pojos.Film;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import static com.gmail.nf.project.jddca.noticefilm.model.db.DatabaseState.*;
import static com.gmail.nf.project.jddca.noticefilm.model.utils.FirebaseService.getDatabaseReference;

public class DatabaseServiceImpl implements DatabaseService {

    private static final String FAVORITES_MOVIES = "favorites_movies";
    private static final String LIST_MOVIES = "list_movies";


    @Override
    public void saveToFavoritesMovie(Film film) {
        getFavoritesNodeRef(film.getId()).setValue(film);
    }

    @Override
    public void saveToListMovie(Film film) {
        getListNodeRef(film.getId()).setValue(film);
    }

    @Override
    public void removeFavoritesMovie(Film film) {
        getFavoritesNodeRef(film.getId()).removeValue();
    }

    @Override
    public void removeListsMovie(Film film) {
        getListNodeRef(film.getId()).removeValue();
    }

    @Override
    public void checkInFavoritesMovie(Film film, ResultFromDatabase resultFromDatabase, ErrorFromDatabase errorFromDatabase) {
        getFavoritesNodeRef(film.getId()).addListenerForSingleValueEvent(getSingleReadMovie(resultFromDatabase,errorFromDatabase));
    }

    @Override
    public void checkInListMovie(Film film, ResultFromDatabase resultFromDatabase, ErrorFromDatabase errorFromDatabase) {
        getListNodeRef(film.getId()).addListenerForSingleValueEvent(getSingleReadMovie(resultFromDatabase,errorFromDatabase));
    }

    private DatabaseReference getFavoritesNodeRef(Integer id) {
        if (id != null) {
            String string_id = Integer.toString(id);
            return getDatabaseReference().child(FAVORITES_MOVIES).child(string_id);
        }
        return getDatabaseReference().child(FAVORITES_MOVIES);
    }

    private DatabaseReference getListNodeRef(Integer id) {
        if (id != null) {
            String string_id = Integer.toString(id);
            return getDatabaseReference().child(LIST_MOVIES).child(string_id);
        }
        return getDatabaseReference().child(LIST_MOVIES);
    }

    private ValueEventListener getSingleReadMovie(ResultFromDatabase resultFromDatabase, ErrorFromDatabase errorFromDatabase) {
        return new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean b = dataSnapshot.getValue()!=null;
                resultFromDatabase.addResult(b);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                errorFromDatabase.addError(databaseError.toException());
            }
        };
    }

}
