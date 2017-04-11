
package com.gmail.nf.project.jddca.noticefilm.model.POJOS.REST.Genre_of_films;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ExampleJanr {

    @SerializedName("genres")
    @Expose
    private List<GenreJanr> genres = new ArrayList<GenreJanr>();

    public List<GenreJanr> getListbuku() {
        return genres;
    }

    public List<GenreJanr> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreJanr> genres) {
        this.genres = genres;
    }

}
