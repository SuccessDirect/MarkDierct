package com.direct.success.markdirect.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.direct.success.markdirect.R;
import com.direct.success.markdirect.adapters.OfertasAdapter;
import com.direct.success.markdirect.managers.OfertasApiManager;
import com.direct.success.markdirect.model.Oferta;

import java.util.LinkedList;
import java.util.List;

public class OffersListActivity extends AppCompatActivity {

    private RecyclerView offersRecyclerView;
    private List<Oferta> listOfOfertas = new LinkedList<>();
    private OfertasAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers_list);

        OfertasApiManager ofertasApiManager = new OfertasApiManager();
        ofertasApiManager.setListener(new OfertasApiManager.OfertasApiManagerNewOfertasListener() {
            @Override
            public void onNewOferta(List<Oferta> oferta) {
                listOfOfertas = oferta;
                offersRecyclerView = (RecyclerView) findViewById(R.id.ofertas_list_recyclerView);
                offersRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

                adapter = new OfertasAdapter(getBaseContext(), listOfOfertas);
                offersRecyclerView.setAdapter(adapter);
            }
        });
        ofertasApiManager.newOferta(this);



    }
}
