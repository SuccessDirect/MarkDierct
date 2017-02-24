package com.direct.success.markdirect.managers;

import com.direct.success.markdirect.model.Oferta;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OfertasEntity {
    @SerializedName("ofertas") private List<Oferta> ofertaList;


    public List<Oferta> getOfertaList() {
        return ofertaList;
    }

    public void setOfertaList(List<Oferta> ofertaList) {
        this.ofertaList = ofertaList;
    }
}
