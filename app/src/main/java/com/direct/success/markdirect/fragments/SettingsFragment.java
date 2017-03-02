package com.direct.success.markdirect.fragments;


import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.direct.success.markdirect.R;

public class SettingsFragment extends Fragment {
private Switch notificationSwitchButton;
private Switch bluetoothSwitchButton;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        notificationSwitchButton = (Switch) view.findViewById(R.id.fragment_settings___switch_notification_button);
        bluetoothSwitchButton = (Switch) view.findViewById(R.id.fragment_settings___switch_bluetooth_button);
        //Para que el botón se inicialice al estado del bluetooth del movil en ese momento.
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter.isEnabled()) {
            bluetoothSwitchButton.setChecked(true);
        }else{
            bluetoothSwitchButton.setChecked(false);
        }
        notificationSwitchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //Cuando este activo y pulso las desactivo
                }else{
                    //Cuando este apagado y pulso las activo
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

}
