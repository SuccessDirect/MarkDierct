package com.direct.success.markdirect.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.direct.success.markdirect.R;
import com.direct.success.markdirect.model.Bacon;

import io.realm.Realm;
import io.realm.RealmResults;

public class HistoricalFragment extends Fragment {
    private TextView textoTrazabilidad;

    public HistoricalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_historical, container, false);
        textoTrazabilidad = (TextView) view.findViewById(R.id.fragment_historical___text);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Bacon> listOfBaconInRealm = realm.where(Bacon.class).findAllSorted("date");
        String s = "";
        for (Bacon b:listOfBaconInRealm ) {
            s = s + "Major: " + b.getMajor() + " - Minor: " + b.getMinor() + " - Date: " + b.getDate() + "\n";
        }
        textoTrazabilidad.setText(s);


        return view;
    }

}
