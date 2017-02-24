package com.direct.success.markdirect.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.direct.success.markdirect.R;
import com.direct.success.markdirect.model.Oferta;
import com.direct.success.markdirect.views.OfertasViewHolder;

import java.util.List;

public class OfertasAdapter extends RecyclerView.Adapter<OfertasViewHolder>{

    private List<Oferta> listOfOfertas;
    private LayoutInflater layoutInflater;

    public OfertasAdapter(Context context, List<Oferta> listOfOfertas) {
        this.listOfOfertas = listOfOfertas;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public OfertasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_offers, parent, false);
        return new OfertasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OfertasViewHolder holder, int position) {
        Oferta oferta = listOfOfertas.get(position);
        holder.setOferta(oferta);
    }

    @Override
    public int getItemCount() {
        return listOfOfertas.size();
    }
}
