
package com.gmail.nf.project.jddca.noticefilm.model.POJOS.REST.FilmsNew;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatesNewF {

    @SerializedName("maximum")
    @Expose
    private String maximum;
    @SerializedName("minimum")
    @Expose
    private String minimum;

    public String getMaximum() {
        return maximum;
    }

    public void setMaximum(String maximum) {
        this.maximum = maximum;
    }

    public String getMinimum() {
        return minimum;
    }

    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

}
