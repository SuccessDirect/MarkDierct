package com.direct.success.markdirect.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.direct.success.markdirect.R;
import com.direct.success.markdirect.model.Oferta;
import com.squareup.picasso.Picasso;

public class OfertasViewHolder extends RecyclerView.ViewHolder {
    private Oferta oferta;
    private TextView titulo;
    private TextView descripcion;
    private TextView fecha;
    private ImageView imagen;


    public OfertasViewHolder(View itemView) {
        super(itemView);
        titulo = (TextView) itemView.findViewById(R.id.row_offers___offer_text);
        descripcion = (TextView) itemView.findViewById(R.id.row_offers___offer_desc);
        fecha = (TextView) itemView.findViewById(R.id.row_offers___offer_fecha);
        imagen = (ImageView) itemView.findViewById(R.id.row_offers___offer_image);
    }

    public Oferta getOferta() {
        return oferta;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
        titulo.setText(oferta.getTittle());
        descripcion.setText(oferta.getDescription());
        fecha.setText(oferta.getDate());
        //fecha.setText(oferta.getDate().toString());
        Picasso.with(itemView.getContext()).load(oferta.getImageUrl()).into(imagen);
    }
}
