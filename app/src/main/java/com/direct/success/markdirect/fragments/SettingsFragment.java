package com.direct.success.markdirect.fragments;


import android.app.Notification;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.direct.success.markdirect.R;
import com.direct.success.markdirect.utils.Notifications;

import io.realm.Realm;

public class SettingsFragment extends Fragment {
    private SwitchCompat notificationSwitchButton;
    private SwitchCompat bluetoothSwitchButton;
    private Notifications notification;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Ajustes");

        notificationSwitchButton = (SwitchCompat) view.findViewById(R.id.fragment_settings___switch_notification_button);
        bluetoothSwitchButton = (SwitchCompat) view.findViewById(R.id.fragment_settings___switch_bluetooth_button);
        //Para que el botón se inicialice al estado del bluetooth del movil en ese momento.
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter.isEnabled()) {
            bluetoothSwitchButton.setChecked(true);
        }else{
            bluetoothSwitchButton.setChecked(false);
        }
        SharedPreferences prefs = getContext().getSharedPreferences("Preferences",Context.MODE_PRIVATE);
        if(prefs.getBoolean("Notification",true))
        {
            notificationSwitchButton.setChecked(true);
        }else{
            notificationSwitchButton.setChecked(false);
        }
        notificationSwitchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //Cuando este activo y pulso las desactivo
                    SharedPreferences prefs =
                            getContext().getSharedPreferences("Preferences", Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("Notification",true);
                    editor.commit();

                }else{
                    //Cuando este apagado y pulso las activo
                    SharedPreferences prefs =
                            getContext().getSharedPreferences("Preferences", Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("Notification",false);
                    editor.commit();
                }
            }
        });
        bluetoothSwitchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //si el bluetooth esta apagado y pulso lo enciendo
                    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                    if (!mBluetoothAdapter.isEnabled()) {
                        mBluetoothAdapter.enable();
                    }
                }else{
                    //si el bluetooth esta encendido y pulso lo apago
                    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                    if (mBluetoothAdapter.isEnabled()) {
                        mBluetoothAdapter.disable();
                    }
                }
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        bluetoothSwitchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //si el bluetooth esta apagado y pulso lo enciendo
                    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                    if (!mBluetoothAdapter.isEnabled()) {
                        mBluetoothAdapter.enable();
                    }
                }else{
                    //si el bluetooth esta encendido y pulso lo apago
                    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                    if (mBluetoothAdapter.isEnabled()) {
                        mBluetoothAdapter.disable();
                    }
                }
            }
        });
    }
}
