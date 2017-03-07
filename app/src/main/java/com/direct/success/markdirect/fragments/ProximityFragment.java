package com.direct.success.markdirect.fragments;


import android.content.Context;
import android.content.SharedPreferences;
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
        //SharedPreferences prefs = getContext().getSharedPreferences("PERSONALDATA", Context.MODE_PRIVATE);
        //int age = prefs.getInt("AGE", 0);
        //String sex = prefs.getString("SEX", "-");
        //TODO: como guardar el major y el minor que hay que poner aquí
        //ofertasApiManager.newOferta(getContext(), sex, age,0,0);
        ofertasApiManager.newOferta(getContext(), "h", 20,4,34911);

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
