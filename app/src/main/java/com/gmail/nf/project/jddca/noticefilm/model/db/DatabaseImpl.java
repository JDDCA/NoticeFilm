package com.gmail.nf.project.jddca.noticefilm.model.db;


import android.util.Log;

import com.gmail.nf.project.jddca.noticefilm.model.pojos.Film;
import com.gmail.nf.project.jddca.noticefilm.model.utils.FirebaseService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static com.gmail.nf.project.jddca.noticefilm.model.utils.FirebaseService.getDatabaseReference;

public class DatabaseImpl implements Database {

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


}
