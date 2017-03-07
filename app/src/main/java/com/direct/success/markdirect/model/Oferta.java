package com.direct.success.markdirect.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Oferta {
    @SerializedName("titulo") private String tittle;
    //Esto esta puesto temporalmente, en el momento que el backend devuelva la fecha en formato data, se quita.
    //@SerializedName("validez") private Date date;
    @SerializedName("validez") private String date;
    @SerializedName("oferta") private String description;
    @SerializedName("imagen") private String imageUrl;

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

   /* public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
*/
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
