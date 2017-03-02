package com.direct.success.markdirect.managers;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.direct.success.markdirect.model.Oferta;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

public class OfertasApiManager {
    public interface OfertasApiManagerNewOfertasListener{
        public void onNewOferta(List<Oferta> oferta);
    }
    private OfertasApiManagerNewOfertasListener listener;

    public void setListener(OfertasApiManagerNewOfertasListener listener) {
        this.listener = listener;
    }
    private static final String BASEURL="https://demo8112147.mockable.io";

    public void newOferta(Context context){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(BASEURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("JSON", response);
                parseJSON(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY", "Connection went to shit to the tracks");
            }
        });
        queue.add(request);
    }

    private void parseJSON(String response){
        Reader reader = new StringReader(response);
        Gson gson = new GsonBuilder().create();
        OfertasEntity ofertasEntities = gson.fromJson(reader, OfertasEntity.class);

        if(listener != null){
            listener.onNewOferta(ofertasEntities.getOfertaList());
        }
    }

}
