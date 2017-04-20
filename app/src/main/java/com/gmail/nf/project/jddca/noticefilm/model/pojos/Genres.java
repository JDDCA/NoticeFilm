package com.gmail.nf.project.jddca.noticefilm.model.pojos;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.PackagePrivate;

@PackagePrivate
@Setter
@Getter
@ToString
public class Genres {

    @SerializedName("genres")
    @Expose
    private List <Genre> genres = new ArrayList <>();

}
