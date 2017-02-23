package com.direct.success.markdirect.managers;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class OfertasEntity {
    @SerializedName("title") private String title;
    @SerializedName("date") private String dateOff;
    @SerializedName("description") private String description;
    @SerializedName("image") private String imageUrl;

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
