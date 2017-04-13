package com.gmail.nf.project.jddca.noticefilm.model.pojos;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpokenLanguage {

    @SerializedName("iso_639_1")
    @Expose
    private String iso6391;

    @SerializedName("name")
    @Expose
    private String name;

}
