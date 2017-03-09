package com.direct.success.markdirect.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.direct.success.markdirect.R;
import com.direct.success.markdirect.activities.GeneralActivity;
import com.direct.success.markdirect.adapters.OfertasAdapter;
import com.direct.success.markdirect.managers.OfertasApiManager;
import com.direct.success.markdirect.model.Oferta;

import java.util.List;


public class OfertasListFragment extends Fragment {

    private static final int WITHOUTMAJOR = -1;
    private static final int WITHOUTMINOR = -1;
    private RecyclerView recyclerOfertasView;

    private List<Oferta> listOfOfertas;

    public OfertasListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_ofertas_list, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Ofertas Generales");

        recyclerOfertasView = (RecyclerView) view.findViewById(R.id.fragment_ofertas_list___recycler_view);

        OfertasApiManager ofertasApiManager = new OfertasApiManager();
        ofertasApiManager.setListener(new OfertasApiManager.OfertasApiManagerNewOfertasListener() {
            @Override
            public void onNewOferta(List<Oferta> oferta) {
                listOfOfertas = oferta;
                refresh();
            }
        });
        SharedPreferences prefs = getActivity().getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
        String token = prefs.getString("Token", null);
        ofertasApiManager.newOferta(getContext(), WITHOUTMAJOR, WITHOUTMINOR , token);


        // recyclerOfertasView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    /*public void setAdapter(OfertasAdapter adapter) {
        recyclerOfertasView.setAdapter(adapter);
    }*/

    public List<Oferta> getOfertas() {
        return this.listOfOfertas;
    }

    public void setOfertas(List<Oferta> ofertas) {
        this.listOfOfertas = ofertas;
    }

    public void refresh(){
        if (getOfertas() == null){
            return;
        }
        OfertasAdapter adapter = new OfertasAdapter(getContext(),listOfOfertas);

        recyclerOfertasView.setAdapter(adapter);
        recyclerOfertasView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
