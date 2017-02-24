package com.direct.success.markdirect.managers;

import com.google.gson.annotations.SerializedName;

public class OfertasEntity {
    @SerializedName("titulo") private String title;
    @SerializedName("validez") private String dateOff;
    @SerializedName("oferta") private String description;
    @SerializedName("imagen") private String imageUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateOff() {
        return dateOff;
    }

    public void setDateOff(String dateOff) {
        this.dateOff = dateOff;
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
