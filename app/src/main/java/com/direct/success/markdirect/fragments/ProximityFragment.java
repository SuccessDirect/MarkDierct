package com.direct.success.markdirect.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.direct.success.markdirect.R;
import com.direct.success.markdirect.adapters.OfertasAdapter;
import com.direct.success.markdirect.managers.OfertasApiManager;
import com.direct.success.markdirect.model.Oferta;

import java.util.List;


public class ProximityFragment extends Fragment {

    private RecyclerView recyclerProximityOfertasView;

    private List<Oferta> listOfProximityOfertas;


    public ProximityFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_proximity, container, false);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Cerca de Ti");

        recyclerProximityOfertasView = (RecyclerView) view.findViewById(R.id.fragment_proximity___recycler_view);
        OfertasApiManager ofertasApiManager = new OfertasApiManager();
        ofertasApiManager.setListener(new OfertasApiManager.OfertasApiManagerNewOfertasListener() {
            @Override
            public void onNewOferta(List<Oferta> oferta) {
                listOfProximityOfertas = oferta;
                refresh();
            }
        });
        ofertasApiManager.newOferta(getContext());

        return view;
    }
    public void refresh(){
        if (getListOfProximityOfertas() == null){
            return;
        }
        OfertasAdapter adapter = new OfertasAdapter(getContext(),listOfProximityOfertas);

        recyclerProximityOfertasView.setAdapter(adapter);
        recyclerProximityOfertasView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public List<Oferta> getListOfProximityOfertas() {
        return listOfProximityOfertas;
    }

    public void setListOfProximityOfertas(List<Oferta> listOfProximityOfertas) {
        this.listOfProximityOfertas = listOfProximityOfertas;
    }
}
