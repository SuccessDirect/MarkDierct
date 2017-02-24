package com.direct.success.markdirect.model;

import com.google.gson.annotations.SerializedName;

public class Oferta {
    @SerializedName("titulo") private String tittle;
    @SerializedName("validez") private String date;
    @SerializedName("oferta") private String description;
    @SerializedName("imagen") private String imageUrl;

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String  getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
