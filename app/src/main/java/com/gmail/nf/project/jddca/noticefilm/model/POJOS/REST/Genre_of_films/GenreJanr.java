
package com.gmail.nf.project.jddca.noticefilm.model.POJOS.REST.Genre_of_films;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GenreJanr {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
