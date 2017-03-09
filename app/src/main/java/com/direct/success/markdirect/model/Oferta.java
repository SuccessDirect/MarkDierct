package com.direct.success.markdirect.model;

import android.text.format.DateFormat;
import android.text.style.LocaleSpan;

import com.google.gson.annotations.SerializedName;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Oferta {
    @SerializedName("titulo") private String tittle;
    @SerializedName("validez") private long date;
    @SerializedName("oferta") private String description;
    @SerializedName("imagen") private String imageUrl;

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
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

    public String converTimeStampToDate(long time){

        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time*1000);
        String date = DateFormat.format("d-MMMM",cal).toString();

        return date;
    }
}
